package com.minorProject.DigitalLibrary.service;

import com.minorProject.DigitalLibrary.dto.createBookRequest;
import com.minorProject.DigitalLibrary.enums.FilerBookBy;
import com.minorProject.DigitalLibrary.enums.Operator;
import com.minorProject.DigitalLibrary.model.Author;
import com.minorProject.DigitalLibrary.model.Book;
import com.minorProject.DigitalLibrary.enums.BookType;
import com.minorProject.DigitalLibrary.repository.AuthorRepository;
import com.minorProject.DigitalLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate; // <String,Object>

    private final String BOOK_KEY = "book";

    public Book createBook(createBookRequest createBookRequest) {
        Author author = authorRepository.findByEmail(createBookRequest.getAuthorEmail());

        if(author == null) {
            author = authorRepository.save(createBookRequest.toAuthor());
        }

        Book book = createBookRequest.toBook();
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

//        redisTemplate.opsForList().leftPushAll(BOOK_KEY, savedBook);
//        redisTemplate.opsForList().leftPushAll(BOOK_KEY + savedBook.getId(), savedBook);
//        redisTemplate.opsForList().leftPushAll(BOOK_KEY + savedBook.getBookName(), savedBook);
//        redisTemplate.opsForList().leftPushAll(BOOK_KEY + savedBook.getBookNo(), savedBook);
//
//        redisTemplate.expire(BOOK_KEY, Duration.ofMinutes(10));

        return savedBook;
    }

    public Book getBook(int id) {
//        List<Object> bookList = redisTemplate.opsForList().range(BOOK_KEY + id, 0, -1);
//
//        if(bookList != null && !bookList.isEmpty()) {
//            return (Book) bookList.get(0);
//        }

        return bookRepository.findById(id).orElse(null);
    }


    public List<Book> getAllBooks() {

//        List<Object> books = redisTemplate.opsForList().range(BOOK_KEY, 0, -1);
//
//        if(books != null) {
//            return books.stream().map(book -> (Book) book).collect(Collectors.toList());
//        }
        return bookRepository.findAll();
    }


    public Book updateBook(int id, createBookRequest createBookRequest) {

        redisTemplate.opsForList().leftPushAll(BOOK_KEY + id, createBookRequest);


       Book book = bookRepository.findById(id).get();

       book.setBookName(createBookRequest.getBookName());
       book.setBookType(BookType.valueOf(createBookRequest.getBookType()));
       book.setBookNo(createBookRequest.getBookNo());
       book.setPages(createBookRequest.getPages());
       book.setCost(createBookRequest.getCost());
       book.setProducer(createBookRequest.getProducer());

       return bookRepository.save(book);

    }

    public List<Book> filterBook(Operator operator, FilerBookBy filterBookBy, String value) {
        switch (operator){
            case EQUALS :
                switch (filterBookBy){
                    case BOOK_NAME :
                        List<Object> bookList = redisTemplate.opsForList().range(BOOK_KEY + value, 0, -1);

                        if(bookList !=null){
                            return bookList.stream()
                                    .map(book -> (Book) book)
                                    .toList();
                        }

                        return bookRepository.findByBookName(value);
                    case BOOK_NO :
                        return bookRepository.findByBookNo(value);
                    case COST:
                        return bookRepository.findByCost(Integer.parseInt(value));
                    case PAGES:
                        return bookRepository.findByPages(Integer.parseInt(value));
                }
            case GREATER_THAN:
                switch (filterBookBy){
                    case COST:
                        return bookRepository.findByCostGreaterThan(Integer.parseInt(value));
                    case PAGES:
                        return bookRepository.findByPagesGreaterThan(Integer.parseInt(value));
                }
            case LESS_THAN:
                switch (filterBookBy){
                    case COST:
                        return bookRepository.findByCostLessThan(Integer.parseInt(value));
                    case PAGES:
                        return bookRepository.findByPagesLessThan(Integer.parseInt(value));
                }
            default:
                return new ArrayList<>();
        }
    }

    public void updateBookSave(Book book) {
        bookRepository.save(book);
    }
}

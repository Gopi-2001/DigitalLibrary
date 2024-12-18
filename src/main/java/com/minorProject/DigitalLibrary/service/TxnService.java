package com.minorProject.DigitalLibrary.service;

import com.minorProject.DigitalLibrary.dto.createTxnRequest;
import com.minorProject.DigitalLibrary.dto.returnTxnRequest;
import com.minorProject.DigitalLibrary.enums.FilerBookBy;
import com.minorProject.DigitalLibrary.enums.FilterStudentBy;
import com.minorProject.DigitalLibrary.enums.Operator;
import com.minorProject.DigitalLibrary.enums.TxnStatus;
import com.minorProject.DigitalLibrary.model.Book;
import com.minorProject.DigitalLibrary.model.Student;
import com.minorProject.DigitalLibrary.model.Txn;
import com.minorProject.DigitalLibrary.repository.TxnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TxnService {
    @Autowired
    TxnRepository txnRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    public int createTxn(createTxnRequest createTxnRequest) throws Exception {
        List<Student>  students = studentService.filterStudent(Operator.EQUALS, FilterStudentBy.EMAIL,createTxnRequest.getStudentEmail());
        Student student = students.get(0);

        if(student == null ){
            throw new Exception("Student Not Found");
        }
        List<Book> books = bookService.filterBook(Operator.EQUALS, FilerBookBy.BOOK_NO, createTxnRequest.getBookNo());

        Book book = books.get(0);

        if(book == null){
            throw new Exception("Book Not Found");
        }

        Txn txn = Txn.builder()
                    .student(student)
                    .book(book)
                    .txnStatus(TxnStatus.ISSUED)
                    .paidAmount(createTxnRequest.getPaidAmount())
                    .build();

        txnRepository.save(txn);

        book.setStudent(student);
        bookService.updateBookSave(book);

        return txn.getId();
    }

    public String returnBook(returnTxnRequest returnTxnRequest) throws Exception {
        List<Student>  students = studentService.filterStudent(Operator.EQUALS, FilterStudentBy.EMAIL,returnTxnRequest.getStudentEmail());
        Student student = students.get(0);

        if(student == null ){
            throw new Exception("Student Not Found");
        }
        List<Book> books = bookService.filterBook(Operator.EQUALS, FilerBookBy.BOOK_NO, returnTxnRequest.getBookNo());

        Book book = books.get(0);

        if(book == null){
            throw new Exception("Book Not Found");
        }

        Txn txn = txnRepository.findById(returnTxnRequest.getTxnId()).get();


        if(txn.getTxnStatus() == TxnStatus.ISSUED){
            txn.setTxnStatus(TxnStatus.RETURNED);
        }

        long issuedTime = txn.getCreatedOn().getTime();

        long returnTime = System.currentTimeMillis();

        long diff = returnTime - issuedTime;

        long days = (diff / (1000*60*60*24));

        int fine = 0;

        if(days > 30){
            fine = (int)(days-30) * 5;
            txn.setFine(fine);
            txn.setPaidAmount(txn.getPaidAmount() - fine);
        }

        txnRepository.save(txn);

        book.setStudent(null);
        bookService.updateBookSave(book);
        return "Book Returned Successfully";

    }
}

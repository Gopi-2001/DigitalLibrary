package com.minorProject.DigitalLibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minorProject.DigitalLibrary.enums.TxnStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Txn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int paidAmount;

    private int fine;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updateOn;

    @Enumerated(value = EnumType.STRING)
    private TxnStatus txnStatus;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"txn","book"})
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties({"student","txnList","author"})
    private Book book;
}

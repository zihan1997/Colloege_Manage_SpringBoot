package com.example.demo.book;

import com.example.demo.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "book")
@Entity(name = "Book")
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "student_book_fk"
            )
    )
    private Student student;

    @Column(
            name = "book_name",
            columnDefinition = "text",
            nullable = false
    )
    private String bookName;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "Timestamp without time zone"
    )
    private LocalDateTime createAt;

    public Book() {
    }

    public Book(String bookName, LocalDateTime createAt) {
        this.bookName = bookName;
        this.createAt = createAt;
    }

    public Book(Long id, String bookName, LocalDateTime createAt) {
        this.id = id;
        this.bookName = bookName;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", student=" + student +
                ", bookName='" + bookName + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}

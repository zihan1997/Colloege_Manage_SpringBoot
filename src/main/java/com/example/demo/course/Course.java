package com.example.demo.course;

import javax.persistence.*;

@Entity(name = "Course")
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "text"
    )
    private String name;

    @Column(name = "department", nullable = false, columnDefinition = "text")
    private String department;

}

package com.example.demo.student;

import com.example.demo.book.Book;
import com.example.demo.idcard.StudentIdCard;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo repo){
        return args -> generateStudents(repo);
    }

    private void generateStudents(StudentRepo repo){
        Faker fake = new Faker();
        for(int i = 0; i < 20; i++){
            String firstName = fake.name().firstName();
            String lastName = fake.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);

            Date input = fake.date().birthday();
            LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    date
            );
            StudentIdCard studentIdCard = new StudentIdCard(
                    fake.number().digits(10),
                    student
            );
            student.setStudentIdCard(studentIdCard);

            student.addBook(new Book(
                    fake.book().title(),
                    LocalDateTime.now().minusYears(1)
            ));
        student.addBook(new Book(
                fake.book().title(),
                LocalDateTime.now().minusYears(3)
        ));

            repo.save(student);
        }
    }

}

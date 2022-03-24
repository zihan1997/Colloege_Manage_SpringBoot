package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServices {

    private StudentRepo studentRepo;

    @Autowired
    public StudentServices(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> output = studentRepo.findAllByEmail(student.getEmail());
        if(output.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        if(!studentRepo.existsById(id)){
            throw new IllegalStateException("Student with "+ id +" does not exist");
        }
        studentRepo.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String firstName, String lastName, String email) {
        Student student = studentRepo.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "student with id "+ id + " does not exist"
                ));
        if(firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)){
            student.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)){
            student.setLastName(lastName);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepo.findAllByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException(
                        "email: "+ email + " is taken"
                );
            }
            student.setEmail(email);
        }
    }
}

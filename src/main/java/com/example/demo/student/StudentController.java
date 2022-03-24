package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private StudentServices studentServices;

    @Autowired
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping
    public List<Student> getStudent(){

        return studentServices.getAllStudent();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentServices.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentServices.deleteStudent(id);
    }

    @PutMapping(path = {"{StudentId}"})
    public void updateStudent(
            @PathVariable("StudentId") Long id,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "email", required = false) String email
    ){
        studentServices.updateStudent(id, firstName, lastName, email);
    }
}

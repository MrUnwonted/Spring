package com.arjun.springbootrestapi.controller;

import com.arjun.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(
                1,
                "Arjun",
                "C"
        );
        return student;
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Arjun","C"));
        students.add(new Student(2,"Abhi","A"));
        students.add(new Student(3,"Abi","S"));
        return students;
    }

//    http://localhost:8080/students/1/arjun/c
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable int id,   //Here the id is same as the parameter
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(id,firstName,lastName);
    }

//    http://localhost:8080/students/query?id=1
//    http://localhost:8080/students/query?id=1&firstName=Arjun&lastName=c
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id,firstName,lastName);

    }

//    Spring Boot REST API that handles HTTP POST Reqyest
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody  Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }

    //    Spring Boot REST API that handles HTTP POST Reqyest

    @PutMapping("students/{id}/update")
//    @ResponseStatus(HttpStatus.ACCEPTED) Manually give 200OK
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @DeleteMapping("students/{id}/delete")
    public String deleteStudent( @PathVariable("id") int studentId){
        return "Student Deleted Succefully";
    }

}

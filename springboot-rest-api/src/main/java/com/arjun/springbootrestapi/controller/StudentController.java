package com.arjun.springbootrestapi.controller;

import com.arjun.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Arjun",
                "C"
        );
//        return new ResponseEntity<>( student, HttpStatus.OK);
//        return ResponseEntity.ok(student);
        return ResponseEntity.ok().header("custom-header","arjun")
                .body(student);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Arjun","C"));
        students.add(new Student(2,"Abhi","A"));
        students.add(new Student(3,"Abi","S"));
        return ResponseEntity.ok(students);
    }

//    http://localhost:8080/students/1/arjun/c
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id,   //Here the id is same as the parameter
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student =  new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);
    }

//    http://localhost:8080/students/query?id=1
//    http://localhost:8080/students/query?id=1&firstName=Arjun&lastName=c
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id,firstName,lastName);
        return ResponseEntity.ok(student);

    }

//    Spring Boot REST API that handles HTTP POST Reqyest
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody  Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    //    Spring Boot REST API that handles HTTP POST Reqyest

    @PutMapping("{id}/update")
//    @ResponseStatus(HttpStatus.ACCEPTED) Manually give 200OK
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent( @PathVariable("id") int studentId){
        return ResponseEntity.ok("Student Deleted Succefully");
    }

}

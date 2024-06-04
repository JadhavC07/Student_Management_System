package com.example.Student.controller;

import com.example.Student.entity.Student;
import com.example.Student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile,
                                         @RequestParam("name") String name,
                                         @RequestParam("fatherName") String fatherName,
                                         @RequestParam("lastName") String lastName,
                                         @RequestParam("age") int age,
                                         @RequestParam("dob") String dob) {
        try {
            Student student = new Student();
            student.setName(name);
            student.setFatherName(fatherName);
            student.setLastName(lastName);
            student.setAge(age);
            student.setDob(dob);

            studentService.uploadStudentData(multipartFile, student);

            return ResponseEntity.ok("Data successfully uploaded");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading data");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}

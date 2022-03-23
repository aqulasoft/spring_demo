package com.example.test212.controllers;

import com.example.test212.controllers.exceptions.StudentExistException;
import com.example.test212.controllers.exceptions.StudentNotExistException;
import com.example.test212.controllers.models.StudentRequest;
import com.example.test212.controllers.models.StudentResponse;
import com.example.test212.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    private HashMap<String, StudentRequest> savedStudents = new HashMap<>();

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<StudentResponse> getStudents(@RequestParam(value = "q", required = false) String q) {
        List<StudentResponse> students = studentService.getStudents();
        if (q == null) {
            return students;
        }
        return students.stream()
                .filter(student -> student.getName().contains(q) || String.valueOf(student.getAge()).contains(q))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable("id") String studentId) throws StudentNotExistException {
        return studentService.getStudents(studentId);
    }

    @PostMapping("")
    public String saveStudent(@RequestBody StudentRequest studentRequest) throws StudentExistException {
        return studentService.saveStudent(studentRequest).getId();
    }

    @PutMapping("")
    public void fullUpdateStudent(@RequestBody StudentRequest studentRequest) throws StudentNotExistException {
        studentService.updateStudent(studentRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String studentId) throws StudentNotExistException {
        studentService.deleteStudents(studentId);
    }
}

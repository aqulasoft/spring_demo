package com.example.test212.controllers;

import com.example.test212.controllers.exceptions.StudentExistException;
import com.example.test212.controllers.exceptions.StudentNotExistException;
import com.example.test212.controllers.models.StudentRequest;
import com.example.test212.controllers.models.StudentResponse;
import com.example.test212.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    private HashMap<String, StudentRequest> savedStudents = new HashMap<>();

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<StudentResponse> getStudents() {
        return studentService.getStudents();
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
    public String fullUpdateStudent(@RequestBody StudentRequest studentRequest) {
        savedStudents.put(studentRequest.getId(), studentRequest);
        return studentRequest.getId();
    }

    @PatchMapping("/{id}")
    public StudentResponse changeUser(
            @PathVariable("id") String studentId,
            @RequestBody StudentRequest studentRequest) throws StudentNotExistException {

        if (!savedStudents.containsKey(studentId)) {
            throw new StudentNotExistException();
        }

        StudentRequest student = savedStudents.get(studentId);
        student.setAge(studentRequest.getAge());
        student.setName(studentRequest.getName());
        return convertToResponse(studentId, studentRequest);
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      private
    ///////////////////////////////////////////////////////////////////////////

    private StudentResponse convertToResponse(String id, StudentRequest studentRequest) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(id);
        studentResponse.setName(studentRequest.getName());
        studentResponse.setAge(studentRequest.getAge());
        return studentResponse;
    }
}

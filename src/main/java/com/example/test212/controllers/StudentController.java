package com.example.test212.controllers;

import com.example.test212.controllers.exceptions.StudentNotExistException;
import com.example.test212.controllers.models.StudentRequest;
import com.example.test212.controllers.models.StudentResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private HashMap<String, StudentRequest> savedStudents = new HashMap<>();

    @GetMapping("")
    public List<StudentResponse> getStudents() {
        ArrayList<StudentResponse> result = new ArrayList<>();
        for (Map.Entry<String, StudentRequest> entry : savedStudents.entrySet()) {
            result.add(convertToResponse(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable("id") String studentId) throws StudentNotExistException {

        if (!savedStudents.containsKey(studentId)) {
            throw new StudentNotExistException();
        }

        StudentResponse result = convertToResponse(studentId, savedStudents.get(studentId));
        return result;
    }

    @PostMapping("")
    public String saveStudent(@RequestBody StudentRequest studentRequest) {
        String id = String.valueOf(savedStudents.size());
        savedStudents.put(id, studentRequest);
        return id;
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

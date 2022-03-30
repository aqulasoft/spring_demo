package com.example.test212.services;

import com.example.test212.controllers.exceptions.StudentExistException;
import com.example.test212.controllers.exceptions.StudentNotExistException;
import com.example.test212.controllers.models.StudentDto;
import com.example.test212.controllers.models.StudentRequest;

import java.util.List;

public interface StudentService {

    StudentDto saveStudent(StudentRequest studentRequest) throws StudentExistException;

    List<StudentDto> getStudents();

    StudentDto getStudents(String studentId) throws StudentNotExistException;

    void updateStudent(StudentRequest studentRequest) throws StudentNotExistException;

    void deleteStudents(String studentId) throws StudentNotExistException;
}

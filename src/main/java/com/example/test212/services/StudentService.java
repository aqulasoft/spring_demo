package com.example.test212.services;

import com.example.test212.controllers.exceptions.StudentExistException;
import com.example.test212.controllers.models.StudentRequest;
import com.example.test212.controllers.models.StudentResponse;
import com.example.test212.database.entities.StudentEntity;
import com.example.test212.database.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      public
    ///////////////////////////////////////////////////////////////////////////

    public StudentResponse saveStudent(StudentRequest studentRequest) throws StudentExistException {
        if (studentRequest.getId() != null) {
            StudentEntity existedStudent = studentRepository.getById(studentRequest.getId());
            if (existedStudent != null) {
                throw new StudentExistException();
            }
        }

        StudentEntity newStudent = new StudentEntity();
        newStudent.setAge(studentRequest.getAge());
        newStudent.setName(studentRequest.getName());
        studentRepository.save(newStudent);

        return new StudentResponse(
                newStudent.getId(),
                newStudent.getAge(),
                newStudent.getName()
        );
    }
}

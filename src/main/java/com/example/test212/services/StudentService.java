package com.example.test212.services;

import com.example.test212.controllers.exceptions.StudentExistException;
import com.example.test212.controllers.exceptions.StudentNotExistException;
import com.example.test212.controllers.models.StudentRequest;
import com.example.test212.controllers.models.StudentResponse;
import com.example.test212.database.entities.StudentEntity;
import com.example.test212.database.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (studentRequest.getId() != null && studentRepository.existsById(studentRequest.getId())) {
            throw new StudentExistException();
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

    public List<StudentResponse> getStudents() {
        List<StudentEntity> allStudents = studentRepository.findAll();

        return allStudents.stream()
                .map(studentEntity -> new StudentResponse(
                        studentEntity.getId(),
                        studentEntity.getAge(),
                        studentEntity.getName()
                ))
                .collect(Collectors.toList());
    }

    public StudentResponse getStudents(String studentId) throws StudentNotExistException {
        Optional<StudentEntity> existedStudent = studentRepository.findOptionalById(studentId);

        StudentEntity student = existedStudent.orElseThrow(StudentNotExistException::new);

        return new StudentResponse(
                student.getId(),
                student.getAge(),
                student.getName()
        );
    }
}

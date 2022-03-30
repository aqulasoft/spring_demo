package com.example.test212.services;

import com.example.test212.controllers.exceptions.StudentExistException;
import com.example.test212.controllers.exceptions.StudentNotExistException;
import com.example.test212.controllers.models.StudentRequest;
import com.example.test212.controllers.models.StudentResponse;
import com.example.test212.database.entities.StudentEntity;
import com.example.test212.database.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final ModelMapper mapper;
    private final StudentRepository studentRepository;

    public StudentService(ModelMapper mapper, StudentRepository studentRepository) {
        this.mapper = mapper;
        this.studentRepository = studentRepository;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      public
    ///////////////////////////////////////////////////////////////////////////

    public StudentResponse saveStudent(StudentRequest studentRequest) throws StudentExistException {
        if (studentRequest.getId() != null && studentRepository.existsById(studentRequest.getId())) {
            throw new StudentExistException();
        }

        StudentEntity newStudent = mapper.map(studentRequest, StudentEntity.class);
        studentRepository.save(newStudent);

        return mapper.map(newStudent, StudentResponse.class);
    }

    public List<StudentResponse> getStudents() {
        List<StudentEntity> allStudents = studentRepository.findAll();

        return allStudents.stream()
                .map(studentEntity -> mapper.map(studentEntity, StudentResponse.class))
                .collect(Collectors.toList());
    }

    public StudentResponse getStudents(String studentId) throws StudentNotExistException {
        Optional<StudentEntity> existedStudent = studentRepository.findOptionalById(studentId);

        StudentEntity student = existedStudent.orElseThrow(StudentNotExistException::new);
        return mapper.map(student, StudentResponse.class);
    }

    public void updateStudent(StudentRequest studentRequest) throws StudentNotExistException {
        if (studentRequest.getId() == null || !studentRepository.existsById(studentRequest.getId())) {
            throw new StudentNotExistException();
        }

        StudentEntity student = studentRepository.getById(studentRequest.getId());
        student.setAge(studentRequest.getAge());
        student.setName(studentRequest.getName());
        studentRepository.save(student);
    }

    public void deleteStudents(String studentId) throws StudentNotExistException {
        Optional<StudentEntity> existedStudent = studentRepository.findOptionalById(studentId);

        StudentEntity student = existedStudent.orElseThrow(StudentNotExistException::new);
        studentRepository.delete(student);
    }
}

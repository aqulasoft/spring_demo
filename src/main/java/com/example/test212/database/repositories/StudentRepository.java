package com.example.test212.database.repositories;

import com.example.test212.database.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String> {

    Optional<StudentEntity> findOptionalById(String id);

    boolean existsById(String id);
}

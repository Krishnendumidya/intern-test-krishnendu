package com.example.student.system.repository;
import com.example.student.system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findTop5ByOrderByIdDesc();

    List<Student> findByNameContainingIgnoreCase(String name);
    Optional<Student> findByEmail(String email);

}


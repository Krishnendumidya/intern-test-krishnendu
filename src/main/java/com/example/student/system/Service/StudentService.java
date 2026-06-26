package com.example.student.system.Service;
import com.example.student.system.entity.Student;
import com.example.student.system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {

        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public List<Student> getRecentStudents() {
        return studentRepository.findTop5ByOrderByIdDesc();
    }

    public List<Student> searchStudents(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return studentRepository.findAll();
        }

        try {

            Long id = Long.parseLong(keyword);

            return studentRepository.findById(id)
                    .map(List::of)
                    .orElse(List.of());

        } catch (NumberFormatException e) {

            return studentRepository.findByNameContainingIgnoreCase(keyword);

        }
    }

}


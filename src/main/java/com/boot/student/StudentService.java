package com.boot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();

  }

  public void registerNewStudent(Student student) {
    Optional<Student> studentByEmail = studentRepository.findStudentByEmail(
      student.getEmail());
    if (studentByEmail.isPresent()) {
      throw new IllegalStateException(
        "Student with email " + student.getEmail() + " is Taken ");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {

    boolean exists = studentRepository.existsById(studentId);
    if (!exists) {
      throw new IllegalStateException(
        "Student with id " + studentId + " Not Found");
    }
    studentRepository.deleteById(studentId);
  }

  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
    Student student = studentRepository.findById(studentId).orElseThrow(
      () -> new IllegalStateException(
        "Student with id " + studentId + " Not Found"));

    if (name != null && !name.isEmpty() && !name.equals(student.getName())) {
      student.setName(name);
    }

    if (email != null && !email.isEmpty() && !email.equals(
      student.getEmail())) {
      studentRepository.findStudentByEmail(email).ifPresent((s) -> {
        throw new IllegalStateException("Email " + email + " is Taken");
      });
      student.setEmail(email);
    }
  }

  public Boolean existsEmail(String email) {
    return studentRepository.findStudentByEmail(email).isPresent();
  }
}

package vn.yenthan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.StudentDeleteRequest;
import vn.yenthan.dto.request.StudentRequestDTO;
import vn.yenthan.entity.Student;

import java.util.Map;


public interface StudentService {
    void addStudent(StudentRequestDTO student);
    void updateStudent(Long id, StudentRequestDTO student);
    void deleteStudent(StudentDeleteRequest request);
    Page<Student> getStudents(Pageable pageable, Map<String, Object> condition);
    Student getStudent(Long id);
}

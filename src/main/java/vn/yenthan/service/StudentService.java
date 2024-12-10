package vn.yenthan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.student.StudentRequest;
import vn.yenthan.dto.request.student.StudentUpdateRequest;
import vn.yenthan.dto.response.StudentGPAResponse;
import vn.yenthan.entity.Student;

import java.util.List;
import java.util.Map;


public interface StudentService {
    void addStudent(StudentRequest student);
    void updateStudent(Long id, StudentUpdateRequest student);
    void deleteStudent(IdDeleteRequest request);
    Page<Student> getStudents(Pageable pageable, Map<String, Object> condition);
    Student getStudent(Long id);
    List<StudentGPAResponse> getStudentGPA(String academicPerformance);
}

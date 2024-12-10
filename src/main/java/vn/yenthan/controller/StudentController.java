package vn.yenthan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.yenthan.core.model.PageResponse;
import vn.yenthan.core.model.SuccessResponse;
import vn.yenthan.core.util.ResponseUtil;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.student.StudentRequest;
import vn.yenthan.dto.request.student.StudentUpdateRequest;
import vn.yenthan.dto.response.StudentGPAResponse;
import vn.yenthan.entity.Student;
import vn.yenthan.service.StudentService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Tag(name = "Student Controller")
public class StudentController {

    private final StudentService studentService;

    @Operation(summary = "Create new student", description = "API create new student")
    @PostMapping("/create")
    public SuccessResponse<String> create(@Valid @RequestBody StudentRequest student) {
        studentService.addStudent(student);
        return ResponseUtil.ok(HttpStatus.CREATED.value(), "Student created successfully");
    }

    @Operation(summary = "Update student", description = "API update student")
    @PostMapping("/update/")
    public SuccessResponse<String> update(@Valid @RequestBody StudentUpdateRequest student) {
        studentService.updateStudent(student.getId(), student);
        return ResponseUtil.ok(HttpStatus.ACCEPTED.value(), "Student updated successfully");
    }

    @Operation(summary = "Delete student", description = "API delete student")
    @PostMapping("/delete")
    public SuccessResponse<String> delete(@Valid @RequestBody IdDeleteRequest request) {
        studentService.deleteStudent(request);
        return ResponseUtil.ok(HttpStatus.NO_CONTENT.value(), "Student deleted successfully");
    }

    @Operation(summary = "Get student", description = "API get student with filters")
    @GetMapping
    public PageResponse<Student> getStudents(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "5") int pageSize,
                                             @RequestParam(required = false) Map<String, Object> filters) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseUtil.ok(studentService.getStudents(pageable, filters));
    }

    @Operation(summary = "Get student", description = "API get student with id")
    @GetMapping("/{id}")
    public SuccessResponse<Student> getStudent(@PathVariable Long id) {
        return  ResponseUtil.ok(HttpStatus.OK.value(), studentService.getStudent(id));
    }

    @Operation(summary = "Get student gpa", description = "API get all student's gpa")
    @GetMapping("/gpa")
    public SuccessResponse<List<StudentGPAResponse>> getStudents(
                                                @RequestParam(required = false) String academicPerformance) {
        return ResponseUtil.ok(HttpStatus.OK.value(), studentService.getStudentGPA(academicPerformance));
    }
}

package vn.yenthan.controller;

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
import vn.yenthan.dto.request.StudentRequestDTO;
import vn.yenthan.entity.Student;
import vn.yenthan.service.StudentService;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/create")
    public SuccessResponse<String> create(@Valid @RequestBody StudentRequestDTO student) {
        studentService.addStudent(student);
        return ResponseUtil.ok(HttpStatus.CREATED.value(), "Student created successfully");
    }

    @PostMapping("/update/")
    public SuccessResponse<String> update(@Valid @RequestBody StudentRequestDTO student) {
        studentService.updateStudent(student.getId(), student);
        return ResponseUtil.ok(HttpStatus.ACCEPTED.value(), "Student updated successfully");
    }

    @PostMapping("/delete")
    public SuccessResponse<String> delete(@Valid @RequestBody IdDeleteRequest request) {
        studentService.deleteStudent(request);
        return ResponseUtil.ok(HttpStatus.NO_CONTENT.value(), "Student deleted successfully");
    }

    @GetMapping
    public PageResponse<Student> getStudents(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "5") int pageSize,
                                             @RequestParam(required = false) Map<String, Object> filters) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseUtil.ok(studentService.getStudents(pageable, filters));
    }

    @GetMapping("/{id}")
    public SuccessResponse<Student> getStudent(@PathVariable Long id) {
        return  ResponseUtil.ok(HttpStatus.OK.value(), studentService.getStudent(id));
    }
}

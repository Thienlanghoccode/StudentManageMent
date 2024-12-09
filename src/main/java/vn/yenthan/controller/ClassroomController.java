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
import vn.yenthan.dto.request.classroom.ClassroomRequest;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.student.StudentRequestDTO;
import vn.yenthan.dto.response.ClassroomResponse;
import vn.yenthan.service.ClassroomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping("/create")
    public SuccessResponse<String> create(@Valid @RequestBody ClassroomRequest request) {
        List<StudentRequestDTO> students = request.getStudents();
        classroomService.addClassroom(request, students);
        return ResponseUtil.ok(HttpStatus.CREATED.value(), "Classroom created successfully");
    }

    @PostMapping("/update")
    public SuccessResponse<String> update(@Valid @RequestBody ClassroomRequest request) {
        classroomService.updateClassroom(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Classroom updated successfully");
    }

    @PostMapping("/delete")
    public SuccessResponse<String> delete(@RequestBody IdDeleteRequest request) {
        classroomService.deleteClassroom(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Classroom deleted successfully");
    }

    @GetMapping("/{id}")
    public SuccessResponse<ClassroomResponse> getClassroom(@PathVariable Long id) {
        return  ResponseUtil.ok(HttpStatus.OK.value(), classroomService.getClassroomById(id));
    }

    @GetMapping
    public PageResponse<ClassroomResponse> getClassrooms(@RequestParam(defaultValue = "0") int pageNumber,
                                                         @RequestParam(defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseUtil.ok(classroomService.getClassrooms(pageable));
    }
}

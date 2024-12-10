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
import vn.yenthan.dto.request.classroom.ClassroomRequest;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.classroom.ClassroomUpdateRequest;
import vn.yenthan.dto.request.student.StudentRequest;
import vn.yenthan.dto.response.ClassroomResponse;
import vn.yenthan.service.ClassroomService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classroom")
@RequiredArgsConstructor
@Tag(name = "Classroom Controller")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Operation(summary = "Create new classroom", description = "API create new classroom")
    @PostMapping("/create")
    public SuccessResponse<String> create(@Valid @RequestBody ClassroomRequest request) {
        List<StudentRequest> students = request.getStudents();
        classroomService.addClassroom(request, students);
        return ResponseUtil.ok(HttpStatus.CREATED.value(), "Classroom created successfully");
    }

    @Operation(summary = "Update classroom", description = "API update classroom")
    @PostMapping("/update")
    public SuccessResponse<String> update(@Valid @RequestBody ClassroomUpdateRequest request) {
        classroomService.updateClassroom(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Classroom updated successfully");
    }

    @Operation(summary = "Delete classroom", description = "API delete classroom")
    @PostMapping("/delete")
    public SuccessResponse<String> delete(@RequestBody IdDeleteRequest request) {
        classroomService.deleteClassroom(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Classroom deleted successfully");
    }

    @Operation(summary = "Get classroom by id", description = "API get classroom by id")
    @GetMapping("/{id}")
    public SuccessResponse<ClassroomResponse> getClassroom(@PathVariable Long id) {
        return  ResponseUtil.ok(HttpStatus.OK.value(), classroomService.getClassroomById(id));
    }

    @Operation(summary = "Get all classroom with student", description = "API get all classroom with student")
    @GetMapping
    public PageResponse<ClassroomResponse> getClassrooms(@RequestParam(defaultValue = "0") int pageNumber,
                                                         @RequestParam(defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseUtil.ok(classroomService.getClassrooms(pageable));
    }

}

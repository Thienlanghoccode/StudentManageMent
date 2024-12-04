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
import vn.yenthan.dto.request.ClassroomRequest;
import vn.yenthan.dto.request.StudentRequestDTO;
import vn.yenthan.service.ClassroomService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @PostMapping("/create")
    public SuccessResponse<?> create(@Valid @RequestBody ClassroomRequest request) {
        List<StudentRequestDTO> students = request.getStudents();
        classroomService.addClassroom(request, students);
        return ResponseUtil.ok(HttpStatus.CREATED.value(), "Classroom created successfully");
    }

    @GetMapping("/{id}")
    public SuccessResponse<?> getClassroom(@PathVariable Long id) {
        return  ResponseUtil.ok(HttpStatus.OK.value(), classroomService.getClassroomById(id));
    }

    @GetMapping
    public PageResponse<?> getAllClassrooms(@RequestParam(defaultValue = "0") int pageNumber,
                                          @RequestParam(defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseUtil.ok(classroomService.getClassrooms(pageable));
    }
}

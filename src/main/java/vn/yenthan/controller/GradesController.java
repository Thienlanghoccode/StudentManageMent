package vn.yenthan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.yenthan.core.model.PageResponse;
import vn.yenthan.core.model.SuccessResponse;
import vn.yenthan.core.util.ResponseUtil;
import vn.yenthan.dto.request.grades.GradesRequest;
import vn.yenthan.dto.response.SubjectResponse;
import vn.yenthan.service.GradesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grades")
public class GradesController {

    private final GradesService gradesService;

    @PostMapping("/create")
    public SuccessResponse<String> create(@RequestBody GradesRequest request) {
        gradesService.addGrade(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Added grades for student successfully");
    }

    @PostMapping("/update")
    public SuccessResponse<String> update(@RequestBody GradesRequest request) {
        gradesService.updateGrade(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Updated grades for student successfully");
    }

    @GetMapping
    public PageResponse<SubjectResponse> getGradesPage(@RequestParam(defaultValue = "0") int pageNumber,
                                                       @RequestParam(defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<SubjectResponse> result = gradesService.getAllStudentGradesBySubject();
        return ResponseUtil.ok(new PageImpl<>(result, pageable, result.size()));
    }

}

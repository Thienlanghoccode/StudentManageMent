package vn.yenthan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import vn.yenthan.dto.request.grades.GradesUpdateRequest;
import vn.yenthan.dto.response.AverageScoreResponse;
import vn.yenthan.dto.response.SubjectResponse;
import vn.yenthan.service.GradesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grades")
@Tag(name = "Grade Controller")
public class GradesController {

    private final GradesService gradesService;

    @Operation(summary = "Add grades ", description = "API add grades for student")
    @PostMapping("/create")
    public SuccessResponse<String> create(@Valid @RequestBody GradesRequest request) {
        gradesService.addGrade(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Added grades for student successfully");
    }

    @Operation(summary = "Update grades ", description = "API update grades for student")
    @PostMapping("/update")
    public SuccessResponse<String> update(@Valid @RequestBody GradesUpdateRequest request) {
        gradesService.updateGrade(request);
        return ResponseUtil.ok(HttpStatus.OK.value(), "Updated grades for student successfully");
    }

    @Operation(summary = "Get student's grade", description = "API get student's grade by subject")
    @GetMapping
    public PageResponse<SubjectResponse> getGradesPageBySubject(@RequestParam(defaultValue = "0") int pageNumber,
                                                                @RequestParam(defaultValue = "5") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<SubjectResponse> result = gradesService.getAllStudentGradesBySubject();
        return ResponseUtil.ok(new PageImpl<>(result, pageable, result.size()));
    }

    @Operation(summary = "Calculate average score", description = "API calculate average student's score")
    @GetMapping("/average")
    public SuccessResponse<AverageScoreResponse> getGradesAverageScore(
                                                 @RequestParam(defaultValue = "0") String studentCode) {
        return ResponseUtil.ok(HttpStatus.OK.value(), gradesService.getAverageScoreByStudentCode(studentCode));
    }

}

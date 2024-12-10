package vn.yenthan.service;

import vn.yenthan.dto.request.grades.GradesRequest;
import vn.yenthan.dto.request.grades.GradesUpdateRequest;
import vn.yenthan.dto.response.AverageScoreResponse;
import vn.yenthan.dto.response.SubjectResponse;

import java.util.List;

public interface GradesService {
    void addGrade(GradesRequest request);
    void updateGrade(GradesUpdateRequest request);
    List<SubjectResponse> getAllStudentGradesBySubject();
    AverageScoreResponse getAverageScoreByStudentCode(String studentCode);
}

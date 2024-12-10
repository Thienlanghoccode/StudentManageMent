package vn.yenthan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.yenthan.dto.request.grades.GradesRequest;
import vn.yenthan.dto.request.grades.GradesUpdateRequest;
import vn.yenthan.dto.response.AverageScoreResponse;
import vn.yenthan.dto.response.StudentGradesResponse;
import vn.yenthan.dto.response.SubjectResponse;
import vn.yenthan.entity.Grades;
import vn.yenthan.entity.Subject;
import vn.yenthan.exception.payload.DataNotFoundException;
import vn.yenthan.mapper.GradesMapper;
import vn.yenthan.repository.GradesRepository;
import vn.yenthan.repository.SubjectRepository;
import vn.yenthan.service.GradesService;
import vn.yenthan.util.GPAUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradesServiceImpl implements GradesService {

    private final GradesRepository gradesRepository;

    private final GradesMapper gradesMapper;

    private final SubjectRepository subjectRepository;


    @Override
    @Transactional
    public void addGrade(GradesRequest request) {
        Grades grades = gradesMapper.toGrades(request);
        gradesRepository.save(grades);
    }

    @Override
    @Transactional
    public void updateGrade(GradesUpdateRequest request) {
        Grades grades = gradesRepository.findById(request.getId())
                .orElseThrow(() -> new DataNotFoundException("Grades not found"));
        grades.setSubjectCode(request.getSubjectCode());
        grades.setStudentCode(request.getStudentCode());
        grades.setScore(request.getScore());
        gradesRepository.save(grades);
    }

    @Override
    public List<SubjectResponse> getAllStudentGradesBySubject() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectResponse> subjectResponses = new ArrayList<>();
        for (Subject subject : subjects) {
            SubjectResponse subjectResponse = new SubjectResponse();
            subjectResponse.setSubject(subject);
            List<Object[]> result = gradesRepository.findAllStudentGrades(subject.getSubjectCode());
            List<StudentGradesResponse> responses = result.stream().map(
                    row -> new StudentGradesResponse(
                            (String) row[0],
                            (String) row[1],
                            (Double) row[2]
                    )
            ).toList();
            subjectResponse.setStudents(responses);
            subjectResponses.add(subjectResponse);
        }
        return subjectResponses;
    }

    @Override
    public AverageScoreResponse getAverageScoreByStudentCode(String studentCode) {
        AverageScoreResponse response = new AverageScoreResponse();
        Double gpa = gradesRepository.getAverageScoreByStudentCode(studentCode);
        response.setGpa(gpa);
        response.setAcademicPerformance(GPAUtil.classifyGPA(gpa));
        return response;
    }
}

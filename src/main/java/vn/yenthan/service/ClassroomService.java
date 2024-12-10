package vn.yenthan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.classroom.ClassroomRequest;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.classroom.ClassroomUpdateRequest;
import vn.yenthan.dto.request.student.StudentRequest;
import vn.yenthan.dto.response.ClassroomResponse;

import java.util.List;

public interface ClassroomService {
    void addClassroom(ClassroomRequest classroomUpdateRequest, List<StudentRequest> studentRequest);
    ClassroomResponse getClassroomById(Long id);
    Page<ClassroomResponse> getClassrooms(Pageable pageable);
    void updateClassroom(ClassroomUpdateRequest classroomRequest);
    void deleteClassroom(IdDeleteRequest request);
}

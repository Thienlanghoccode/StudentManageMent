package vn.yenthan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.classroom.ClassroomRequest;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.student.StudentRequestDTO;
import vn.yenthan.dto.response.ClassroomResponse;

import java.util.List;

public interface ClassroomService {
    void addClassroom(ClassroomRequest classroomRequest, List<StudentRequestDTO> studentRequestDTO);
    ClassroomResponse getClassroomById(Long id);
    Page<ClassroomResponse> getClassrooms(Pageable pageable);
    void updateClassroom(ClassroomRequest classroomRequest);
    void deleteClassroom(IdDeleteRequest request);
}

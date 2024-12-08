package vn.yenthan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.ClassroomRequest;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.StudentRequestDTO;
import vn.yenthan.dto.response.ClassroomResponse;
import vn.yenthan.entity.Classroom;

import java.util.List;

public interface ClassroomService {
    void addClassroom(ClassroomRequest classroomRequest, List<StudentRequestDTO> studentRequestDTO);
    ClassroomResponse getClassroomById(Long id);
    Page<ClassroomResponse> getClassrooms(Pageable pageable);
    void updateClassroom(ClassroomRequest classroomRequest);
    void deleteClassroom(IdDeleteRequest request);
}

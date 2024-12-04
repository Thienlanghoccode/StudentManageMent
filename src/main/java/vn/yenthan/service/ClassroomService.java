package vn.yenthan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.ClassroomRequest;
import vn.yenthan.dto.request.StudentRequestDTO;
import vn.yenthan.entity.Classroom;

import java.util.List;

public interface ClassroomService {
    void addClassroom(ClassroomRequest classroomRequest, List<StudentRequestDTO> studentRequestDTO);
    Classroom getClassroomById(Long id);
    Page<Classroom> getClassrooms(Pageable pageable);
}

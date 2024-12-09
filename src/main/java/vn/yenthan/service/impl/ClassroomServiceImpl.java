package vn.yenthan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.yenthan.dto.request.classroom.ClassroomRequest;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.student.StudentRequestDTO;
import vn.yenthan.dto.response.ClassroomResponse;
import vn.yenthan.entity.Classroom;
import vn.yenthan.entity.Student;
import vn.yenthan.entity.StudentClass;
import vn.yenthan.exception.payload.DataNotFoundException;
import vn.yenthan.mapper.ClassroomMapper;
import vn.yenthan.mapper.StudentMapper;
import vn.yenthan.repository.ClassroomRepository;
import vn.yenthan.repository.StudentClassRepository;
import vn.yenthan.repository.StudentRepository;
import vn.yenthan.service.ClassroomService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final ClassroomMapper classroomMapper;

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final StudentClassRepository studentClassRepository;

    @Override
    @Transactional
    public void addClassroom(ClassroomRequest classroomRequest, List<StudentRequestDTO> studentRequestDTO) {
        Classroom classroom = classroomMapper.toClassRoom(classroomRequest);
        classroomRepository.save(classroom);
        addStudentToClass(classroomRequest, studentRequestDTO);
    }

    @Override
    public ClassroomResponse getClassroomById(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Classroom not found"));
        ClassroomResponse classroomResponse = new ClassroomResponse();
        classroomResponse.setClassCode(classroom.getClassCode());
        classroomResponse.setClassName(classroom.getClassName());
        classroomResponse.setDescription(classroom.getDescription());
        List<Student> students = studentRepository.findAllByClassCode(classroom.getClassCode());
        classroomResponse.setStudents(students);
        return classroomResponse;
    }

    @Override
    public Page<ClassroomResponse> getClassrooms(Pageable pageable) {
        List<ClassroomResponse> classroomResponses = new ArrayList<>();
        List<Classroom> classrooms = classroomRepository.findAll(pageable).getContent();
        classrooms.forEach(classroom -> {
            ClassroomResponse classrp = new ClassroomResponse();
            classrp.setClassCode(classroom.getClassCode());
            classrp.setClassName(classroom.getClassName());
            classrp.setDescription(classroom.getDescription());

            List<Student> students = studentRepository.findAllByClassCode(classroom.getClassCode());
            classrp.setStudents(students);
            classroomResponses.add(classrp);
        });
        return new PageImpl<>(classroomResponses, pageable, classroomResponses.size());
    }

    @Override
    @Transactional
    public void updateClassroom(ClassroomRequest classroomRequest) {
        Classroom classroom = classroomRepository.findById(classroomRequest.getId())
                .orElseThrow(() -> new DataNotFoundException("Classroom not found"));
        classroom.setClassCode(classroomRequest.getClassCode());
        classroom.setDescription(classroomRequest.getDescription());
        classroom.setClassName(classroomRequest.getClassName());
        classroomRepository.save(classroom);

        List<StudentRequestDTO> studentRequestDTO = classroomRequest.getStudents();
        addStudentToClass(classroomRequest, studentRequestDTO);
    }

    @Override
    @Transactional
    public void deleteClassroom(IdDeleteRequest request) {
        if(request.getId() != null) {
            if(classroomRepository.existsById(request.getId())) {
                classroomRepository.deleteById(request.getId());
            } else throw new DataNotFoundException("Classroom not found");
        }
        else if (request.getIds() != null && !request.getIds().isEmpty()) {
            List<Student> students = studentRepository.findAllById(request.getIds());
            if (students.isEmpty()) {
                throw new DataNotFoundException("No students found with the provided IDs.");
            }
            classroomRepository.deleteAllById(request.getIds());
        }
    }

    private void addStudentToClass(ClassroomRequest classroomRequest, List<StudentRequestDTO> studentRequestDTO) {
        if (!studentRequestDTO.isEmpty()) {
            studentRequestDTO.forEach(requestDTO -> {
                if (studentRepository.existsByStudentCode(requestDTO.getStudentCode())) {
                    Student student = studentRepository.findByStudentCode(requestDTO.getStudentCode());
                    StudentClass studentClass = new StudentClass();
                    studentClass.setClassCode(classroomRequest.getClassCode());
                    studentClass.setStudentCode(student.getStudentCode());
                    studentClassRepository.save(studentClass);
                } else {
                    Student student = studentMapper.toStudent(requestDTO);
                    studentRepository.save(student);

                    StudentClass studentClass = new StudentClass();
                    studentClass.setClassCode(classroomRequest.getClassCode());
                    studentClass.setStudentCode(student.getStudentCode());

                    studentClassRepository.save(studentClass);
                }
            });
        }
    }
}

package vn.yenthan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.yenthan.dto.request.ClassroomRequest;
import vn.yenthan.dto.request.StudentRequestDTO;
import vn.yenthan.entity.Classroom;
import vn.yenthan.entity.Student;
import vn.yenthan.exception.payload.DataNotFoundException;
import vn.yenthan.mapper.StudentMapper;
import vn.yenthan.repository.ClassroomRepository;
import vn.yenthan.repository.StudentRepository;
import vn.yenthan.service.ClassroomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public void addClassroom(ClassroomRequest classroomRequest, List<StudentRequestDTO> studentRequestDTO) {
        Classroom classroom = new Classroom();
        classroom.setClassCode(classroomRequest.getClassCode());
        classroom.setClassName(classroomRequest.getClassName());
        classroom.setDescription(classroomRequest.getDescription());

        List<Student> students = new ArrayList<>();
        if (studentRequestDTO != null && !studentRequestDTO.isEmpty()) {
            List<String> emails = studentRequestDTO.stream()
                    .map(StudentRequestDTO::getEmail)
                    .collect(Collectors.toList());

            Map<String, Student> existingStudents = studentRepository.findByEmailIn(emails)
                    .stream()
                    .collect(Collectors.toMap(Student::getEmail, student -> student));

            studentRequestDTO.forEach(requestDTO -> {
                Student student;
                if (existingStudents.containsKey(requestDTO.getEmail())) {
                    student = existingStudents.get(requestDTO.getEmail());
                } else {
                    student = studentMapper.toStudent(requestDTO);
                }
//                student.setClassroom(classroom);
                students.add(student);
            });
        }

//        classroom.setStudents(students);
        classroomRepository.save(classroom);
    }

    @Override
    public Classroom getClassroomById(Long id) {
        return classroomRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Classroom not found"));
    }

    @Override
    public Page<Classroom> getClassrooms(Pageable pageable) {
        return classroomRepository.findAll(pageable);
    }
}

package vn.yenthan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.yenthan.dto.request.IdDeleteRequest;
import vn.yenthan.dto.request.StudentRequestDTO;
import vn.yenthan.dto.request.StudentSpecialSearch;
import vn.yenthan.entity.Student;
import vn.yenthan.exception.payload.DataNotFoundException;
import vn.yenthan.mapper.StudentMapper;
import vn.yenthan.repository.StudentRepository;
import vn.yenthan.service.StudentService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public void addStudent(StudentRequestDTO requestDTO) {
        if (studentRepository.existsByStudentCode(requestDTO.getStudentCode())) {
            throw new DataIntegrityViolationException("Student already exists");
        }
        Student student = studentMapper.toStudent(requestDTO);
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void updateStudent(Long id, StudentRequestDTO requestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(()->new DataNotFoundException("Student not found"));
        student.setStudentCode(requestDTO.getStudentCode());
        student.setStudentName(requestDTO.getStudentName());
        student.setDateOfBirth(requestDTO.getDateOfBirth());
        student.setGender(requestDTO.getGender());
        student.setEmail(requestDTO.getEmail());
        student.setPhoneNumber(requestDTO.getPhoneNumber());
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(IdDeleteRequest request) {
        if(request.getId() != null) {
            if(studentRepository.existsById(request.getId())) {
                Student student = studentRepository.findById(request.getId()).orElseThrow(()->new DataNotFoundException("Student not found"));
                student.setIsDeleted(true);
                studentRepository.save(student);
            } else throw new DataNotFoundException("Student not found");
        }
        else if (request.getIds() != null && !request.getIds().isEmpty()) {
            List<Student> students = studentRepository.findAllById(request.getIds());
            if (students.isEmpty()) {
                throw new DataNotFoundException("No students found with the provided IDs.");
            }
            students.forEach(student -> student.setIsDeleted(true));
            studentRepository.saveAll(students);
        }
    }

    @Override
    public Page<Student> getStudents(Pageable pageable, Map<String, Object> condition) {
        StudentSpecialSearch studentSpecialSearch = studentMapper.toStudentSpecialSearch(condition);
        return studentRepository.findStudents(pageable, studentSpecialSearch);
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new DataNotFoundException("Student not found"));
    }
}

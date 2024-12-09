package vn.yenthan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.yenthan.entity.Student;
import vn.yenthan.core.jpa.custom.StudentRepositoryCustom;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {
    boolean existsByStudentCode(String studentCode);
    Student findByStudentCode(String studentCode);

    @Query(value = """
                SELECT s.*
                FROM tbl_student s
                JOIN tbl_student_classroom sc ON s.student_code = sc.student_code
                WHERE sc.class_code LIKE %:classCode%
            """, nativeQuery = true)
    List<Student> findAllByClassCode(@Param("classCode") String classCode);
}

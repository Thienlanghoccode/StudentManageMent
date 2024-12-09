package vn.yenthan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.yenthan.entity.Grades;

import java.util.List;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {

    @Query(value = """
                SELECT s.student_code as studentCode,
                       s.student_name as studentName,
                       g.score as score
                FROM tbl_grades g
                INNER JOIN tbl_student s ON g.student_code = s.student_code
                WHERE g.subject_code = :subjectCode
            """, nativeQuery = true)
    List<Object[]> findAllStudentGrades(@Param("subjectCode") String subjectCode);
}

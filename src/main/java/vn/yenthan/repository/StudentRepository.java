package vn.yenthan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.yenthan.entity.Student;
import vn.yenthan.repository.custom.StudentRepositoryCustom;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {
    boolean existsByEmail(String email);
    List<Student> findByEmailIn(List<String> email);
}

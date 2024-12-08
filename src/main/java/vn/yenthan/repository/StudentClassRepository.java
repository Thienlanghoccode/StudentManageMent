package vn.yenthan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.yenthan.entity.StudentClass;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, String> {
}

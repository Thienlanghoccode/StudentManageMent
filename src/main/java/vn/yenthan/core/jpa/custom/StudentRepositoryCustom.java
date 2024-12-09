package vn.yenthan.core.jpa.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.student.StudentSpecialSearch;
import vn.yenthan.entity.Student;

public interface StudentRepositoryCustom {
    Page<Student> findStudents(Pageable pageable, StudentSpecialSearch studentSpecialSearch);
}

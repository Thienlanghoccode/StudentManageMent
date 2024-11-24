package vn.yenthan.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.yenthan.dto.request.StudentSpecialSearch;
import vn.yenthan.entity.Student;

public interface StudentRepositoryCustom {
    Page<Student> findStudents(Pageable pageable, StudentSpecialSearch studentSpecialSearch);
}

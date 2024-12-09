package vn.yenthan.core.jpa.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import vn.yenthan.dto.request.student.StudentSpecialSearch;
import vn.yenthan.entity.Student;
import vn.yenthan.core.jpa.custom.StudentRepositoryCustom;

import java.lang.reflect.Field;

import static vn.yenthan.core.jpa.util.ToSnakeCaseUtil.toSnakeCase;

@Repository
@Primary
public class StudentRepositoryCustomImpl implements StudentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Student> findStudents(Pageable pageable, StudentSpecialSearch studentSpecialSearch) {
        StringBuilder sql = new StringBuilder("select s.* FROM tbl_student s ");
        sql.append("WHERE 1 = 1 ");
        queryByParams(sql, studentSpecialSearch);
        Query query = entityManager.createNativeQuery(sql.toString(), Student.class);
        return new PageImpl<>(query.getResultList());
    }

    public static void queryByParams(StringBuilder sql, StudentSpecialSearch studentSpecialSearch) {
        if (studentSpecialSearch != null) {
            Field[] fields = studentSpecialSearch.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value;
                try {
                    value = field.get(studentSpecialSearch);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage());
                }
                if (value != null && !value.toString().isBlank()) {
                    if (field.getType().equals(String.class)) {
                        String snakeCaseFieldName = toSnakeCase(fieldName);
                        sql.append(" AND s.").append(snakeCaseFieldName).append(" LIKE '%").append(value).append("%' ");
                    }
                }
            }
        }
    }
}

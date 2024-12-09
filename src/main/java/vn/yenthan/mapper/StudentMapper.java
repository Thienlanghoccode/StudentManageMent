package vn.yenthan.mapper;


import org.mapstruct.Mapper;
import vn.yenthan.core.util.MapUtil;
import vn.yenthan.dto.request.student.StudentRequestDTO;
import vn.yenthan.dto.request.student.StudentSpecialSearch;
import vn.yenthan.entity.Student;

import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract Student toStudent(StudentRequestDTO student);

    public StudentSpecialSearch toStudentSpecialSearch(Map<String, Object> conditions) {
        new StudentSpecialSearch();
        return StudentSpecialSearch.builder()
                .studentCode(MapUtil.getObject(conditions, "studentCode", String.class))
                .studentName(MapUtil.getObject(conditions, "studentName", String.class))
                .build();

    }
}

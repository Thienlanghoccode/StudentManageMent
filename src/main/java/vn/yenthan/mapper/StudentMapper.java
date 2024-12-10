package vn.yenthan.mapper;


import org.mapstruct.Mapper;
import vn.yenthan.core.util.MapUtil;
import vn.yenthan.dto.request.student.StudentRequest;
import vn.yenthan.dto.request.student.StudentSpecialSearch;
import vn.yenthan.entity.Student;

import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract Student toStudent(StudentRequest student);

    public StudentSpecialSearch toStudentSpecialSearch(Map<String, Object> conditions) {
        new StudentSpecialSearch();
        return StudentSpecialSearch.builder()
                .studentCode(MapUtil.getObject(conditions, "studentCode", String.class))
                .studentName(MapUtil.getObject(conditions, "studentName", String.class))
                .gender(MapUtil.getObject(conditions, "gender", String.class))
                .classCode(MapUtil.getObject(conditions, "classCode", String.class))
                .age(MapUtil.getObject(conditions, "age", Integer.class))
                .build();

    }
}

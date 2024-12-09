package vn.yenthan.mapper;

import org.mapstruct.Mapper;
import vn.yenthan.dto.request.grades.GradesRequest;
import vn.yenthan.entity.Grades;

@Mapper(componentModel = "spring")
public interface GradesMapper {
    Grades toGrades(GradesRequest request);
}

package vn.yenthan.mapper;

import org.mapstruct.Mapper;
import vn.yenthan.dto.request.classroom.ClassroomRequest;
import vn.yenthan.entity.Classroom;

@Mapper(componentModel = "spring")
public interface ClassroomMapper {

    Classroom toClassRoom (ClassroomRequest classroomRequest);
}

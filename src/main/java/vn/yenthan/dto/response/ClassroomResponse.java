package vn.yenthan.dto.response;

import lombok.Getter;
import lombok.Setter;
import vn.yenthan.entity.Student;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class ClassroomResponse implements Serializable {
    private String classCode;
    private String className;
    private String description;

    private List<Student> students;
}

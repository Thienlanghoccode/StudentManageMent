package vn.yenthan.dto.request.classroom;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import vn.yenthan.dto.request.student.StudentRequest;

import java.io.Serializable;
import java.util.List;

@Getter
public class ClassroomRequest implements Serializable {

    @NotBlank(message = "Class code must not be blank !")
    private String classCode;

    @NotBlank(message = "Class name must not be blank !")
    private String className;

    @NotNull(message = "Description must not be null !")
    private String description;

    @NotNull(message = "student list must not be null")
    private List<StudentRequest> students;
}

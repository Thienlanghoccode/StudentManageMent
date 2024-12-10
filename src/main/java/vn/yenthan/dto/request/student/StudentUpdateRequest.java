package vn.yenthan.dto.request.student;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class StudentUpdateRequest extends StudentRequest implements Serializable {

    @NotNull(message = "ID must not be null")
    private Long id;
}

package vn.yenthan.dto.request.classroom;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ClassroomUpdateRequest extends ClassroomRequest implements Serializable {

    @NotNull(message = "ID must not be null")
    private Long id;
}

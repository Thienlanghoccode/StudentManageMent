package vn.yenthan.dto.request.grades;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class GradesUpdateRequest extends GradesRequest implements Serializable {

    @NotNull(message = "ID must not be null")
    private Long id;
}

package vn.yenthan.dto.request.grades;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class GradesRequest implements Serializable  {

    @NotBlank(message = "student code must not be blank")
    private String studentCode;

    @NotBlank(message = "subject code must not be blank")
    private String subjectCode;

    @NotNull(message = "score must not be blank")
    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 10, message = "Score must not be exceed 10")
    private Double score;
}

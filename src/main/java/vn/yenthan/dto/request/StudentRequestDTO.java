package vn.yenthan.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import vn.yenthan.enums.Gender;

import java.util.Date;

@Getter
public class StudentRequestDTO {

    @NotBlank(message = "student's code must not be blank !")
    private String studentCode;

    @NotBlank(message = "student's name must not be blank !")
    private String studentName;

    @NotNull(message = "student's birthday must not be null !")
    private Date birthDay;

    @NotNull(message = "student's gender must not be null !")
    private Gender gender;

    @NotBlank(message = "student's email must not be blank !")
    private String email;

    @NotBlank(message = "student's phone must not be blank !")
    @Min(value = 1000000000, message = "student's phone must have at least 10 digits!")
    private String phoneNumber;
}

package vn.yenthan.dto.request.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentSpecialSearch {
    private String studentCode;
    private String studentName;
    private String gender;
    private String classCode;
    private Integer age;
}

package vn.yenthan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class StudentGradesResponse implements Serializable {

    private String studentCode;

    private String studentName;

    private Double score;
}

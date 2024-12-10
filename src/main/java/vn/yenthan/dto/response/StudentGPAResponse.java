package vn.yenthan.dto.response;

import lombok.Getter;
import lombok.Setter;
import vn.yenthan.entity.Student;

import java.io.Serializable;

@Getter
@Setter
public class StudentGPAResponse implements Serializable {

    private Student student;

    private AverageScoreResponse averageScore;
}

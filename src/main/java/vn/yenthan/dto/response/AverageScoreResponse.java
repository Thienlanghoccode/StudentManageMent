package vn.yenthan.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AverageScoreResponse implements Serializable {

    private double gpa;

    private String academicPerformance;
}

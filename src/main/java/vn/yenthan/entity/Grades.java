package vn.yenthan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;

@Entity
@Table(name = "tbl_grades")
@Getter
@Setter
public class Grades extends EntityBase {

    @Column(name = "student_code", length = 20)
    private String studentCode;

    @Column(name = "subject_code", length = 20)
    private String subjectCode;

    @Column(name = "score", nullable = false)
    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 10, message = "Score must not be exceed 10")
    private Double score;
}

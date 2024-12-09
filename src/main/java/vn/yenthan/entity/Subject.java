package vn.yenthan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;

@Entity
@Table(name = "tbl_subject")
@Getter
@Setter
public class Subject extends EntityBase {

    @Column(name = "subject_code", unique = true, nullable = false, length = 20)
    private String subjectCode;

    @Column(name = "subject_name", length = 100)
    private String subjectName;

    @Column(name = "description")
    private String description;
}

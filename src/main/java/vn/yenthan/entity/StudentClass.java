package vn.yenthan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;

@Entity
@Table(name = "student_classroom")
@Getter
@Setter
public class StudentClass extends EntityBase {

    @Column(name = "student_code")
    private String studentCode;

    @Column(name = "class_code")
    private String classCode;
}

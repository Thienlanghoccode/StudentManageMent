package vn.yenthan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;

@Entity
@Table(name = "stud_classroom")
@Getter
@Setter
public class Classroom extends EntityBase {

    @Column(name = "class_code",nullable = false, unique = true)
    private String classCode;

    @Column(name = "class_name", length = 100)
    private String className;

    @Column(name = "description")
    private String description;

}

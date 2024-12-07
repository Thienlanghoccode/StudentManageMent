package vn.yenthan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;

@Entity
@Table(name = "classroom")
@Getter
@Setter
public class Classroom extends EntityBase {

    @Column(nullable = false, unique = true)
    private String classCode;

    private String className;

    private String description;


}

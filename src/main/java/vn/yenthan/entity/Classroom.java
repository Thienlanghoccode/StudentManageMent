package vn.yenthan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classroom")
@Getter
@Setter
public class Classroom extends EntityBase {

    @Column(nullable = false, unique = true)
    private String classCode;

    private String className;

    private String description;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

}

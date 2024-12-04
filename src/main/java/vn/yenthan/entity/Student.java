package vn.yenthan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;
import vn.yenthan.enums.Gender;

import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student extends EntityBase {

    private String studentCode;

    private String studentName;

    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @JsonBackReference
    private Classroom classroom;

}

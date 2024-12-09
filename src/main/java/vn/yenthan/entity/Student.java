package vn.yenthan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.core.util.EntityBase;
import vn.yenthan.enums.Gender;

import java.util.Date;

@Entity
@Table(name = "tbl_student")
@Getter
@Setter
public class Student extends EntityBase {

    @Column(name = "student_code", nullable = false, unique = true, length = 20)
    private String studentCode;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

}

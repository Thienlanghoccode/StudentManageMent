package vn.yenthan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.yenthan.enums.Gender;

import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentCode;

    private String studentName;

    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private String phoneNumber;

}

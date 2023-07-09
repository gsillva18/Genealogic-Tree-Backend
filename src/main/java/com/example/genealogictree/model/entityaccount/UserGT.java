package com.example.genealogictree.model.entityaccount;

import com.example.genealogictree.core.anotations.ExtendedEmailValidator;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@Entity
@Table(name = "TB_USER_GT")
public class UserGT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(unique = true, name = "email")
    @ExtendedEmailValidator
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be longer than 6 characters")
    @Column(name = "password")
    private String password;

    @Lob
    @Column(name = "profile_picture")
    private String profilePicture;

}

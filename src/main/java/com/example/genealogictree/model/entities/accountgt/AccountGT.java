package com.example.genealogictree.model.entities.accountgt;

import com.example.genealogictree.core.validation.ExtendedEmailValidator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
@Setter
@Getter
@Table(name = "users")
public class AccountGT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(unique = true, name = "Email")
    @ExtendedEmailValidator
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be longer than 6 characters")
    @Column(name = "password")
    private String password;

    @Embedded
    private UserGT userGT;
}

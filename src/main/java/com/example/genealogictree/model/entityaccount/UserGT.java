package com.example.genealogictree.model.entityaccount;

import com.example.genealogictree.core.anotations.ExtendedEmailValidator;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @Column(name = "id_user_auth0")
    private String idUserAuth0;

}

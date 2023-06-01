package com.example.genealogictree.model.entityaccount;

import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "TB_ACCOUNT_GT")
public class AccountGT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "account_gt_fk")
    private List<GenealogicTree> genealogicTreeList;

    @OneToOne
    @JoinColumn(name = "user_gt_fk")
    private UserGT userGT;
}

package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@Data
@Entity
@Table(name = "tbl_user_role")
@AllArgsConstructor // jpa
@NoArgsConstructor // jpa
public class UserRoleVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer userRoleId;

    @Column(name ="role_name", nullable = false, unique = true, length = 128)
    private String userRoleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserVO user;


}

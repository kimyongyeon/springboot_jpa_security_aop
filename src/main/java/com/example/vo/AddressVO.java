package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@Data
@Entity
@Table(name = "tbl_address")
@AllArgsConstructor // jpa
@NoArgsConstructor // jpa
public class AddressVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long addrId;

    @Column(name ="address1", length = 256)
    private String address1;

    @Column(name ="address2", length = 256)
    private String address2;

    @Column(name ="addrCity", length = 256)
    private String addrCity;

    @Column(name ="addrState", length = 64)
    private String addrState;

    @Column(name ="zipCode", length = 32)
    private String zipCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserVO user;

}

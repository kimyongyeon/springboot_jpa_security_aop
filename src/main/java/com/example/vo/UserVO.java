package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@Data
@Table(name="tbl_user")
@Entity // jpa
@AllArgsConstructor // jpa
@NoArgsConstructor // jpa
@NamedQueries({
    @NamedQuery(name="UserVO.findAll",
            query="SELECT c FROM UserVO c"),
    @NamedQuery(name="UserVO.findByUserName",
            query="SELECT c FROM UserVO c WHERE c.user_name = :user_name"),
})
public class UserVO implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", unique = true, length = 64, nullable=false)
    private String user_name;

    @Column(name = "password", length = 256, nullable=false)
    private String userPasswd;

    @Column(name = "first_name", length = 128)
    private String firstName;

    @Column(name = "last_name", length = 128)
    private String lastName;


    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<AddressVO> addressSet = new HashSet<AddressVO>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<UserRoleVO> userRoles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPasswd;
    }

    @Override
    public String getUsername() {
        return this.user_name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

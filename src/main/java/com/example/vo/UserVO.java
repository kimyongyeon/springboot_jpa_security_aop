package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.hibernate.annotations.DynamicUpdate;
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
//@DynamicUpdate // 컬럼이 30개 이상이 되면 기본 방법인 정적 수정 쿼리보다 동적 수정 쿼리가 빠르다.
// 플러시(flush)는 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영한다.
// 컨텍스트를 플러시하는 방법 3가지
// 1. em.flush()를 직접 호출한다.
// 2. 트랜잭션 커밋 시 플러시가 자동 호출 된다.
// 3. jPQL 쿼리 실행시 플러시가 자동 호출 된다.
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

    // LAZY : 지연로딩 , 최적화 필요시 FETCH JOIN 사용
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

package com.example.service;

import com.example.repository.MemberRepository;
import com.example.vo.UserRoleVO;
import com.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    public EntityManager em;

    public List findWithUserName(String name) {
        return em.createQuery(
                "SELECT u FROM UserVO u WHERE u.user_name LIKE :user_name")
                .setParameter("user_name", name)
                .setMaxResults(10)
                .getResultList();
    }

    /**
     * 로그인 처리
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
        UserVO userVO = new UserVO();
        userVO.setUser_name(username);
        UserVO userVO1 = memberRepository.findByUserName(username);

        try {
            if (userVO1 == null) { // 아이디가 없는 경우
                userVO.setUser_name(username);
                userVO.setUserPasswd(standardPasswordEncoder.encode(""));
                return userVO;
            } else {
                userVO1.setUser_name(userVO1.getUser_name());
                userVO1.setUserPasswd(userVO1.getUserPasswd());
                List<GrantedAuthority> authorityList = buildUserAutority(userVO1.getUserRoles());
                return buildUserForAuthentication(userVO1, authorityList);
            }

        } catch (Exception e) {
            // runtime 오류
            System.out.println(e.getMessage());
        }
        return userVO;
    }

    /**
     * 인증 및 권한
     */
    static class SecurityUser extends org.springframework.security.core.userdetails.User {
        public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

        public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        }
    }

    /**
     * 인증
     *
     * @param user
     * @param authorities
     * @return
     */
    private SecurityUser buildUserForAuthentication(UserVO user, List<GrantedAuthority> authorities) {
        return new SecurityUser(user.getUsername(), user.getPassword(), true
                , true, true, true, authorities);
    }

    /**
     * 권한
     *
     * @param userRoles
     * @return
     */
    private List<GrantedAuthority> buildUserAutority(Set<UserRoleVO> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>(0);
        for (UserRoleVO userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getUserRoleName()));
        }
        return authorities;
    }

}

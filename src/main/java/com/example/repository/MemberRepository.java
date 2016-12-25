package com.example.repository;

import com.example.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@Repository
public interface MemberRepository extends JpaRepository<UserVO, Long>{

    UserVO findByUserName(@Param("user_name") String user_name);

    List<UserVO> findByUserIdLessThan(Long userId);

    @Query("select t from UserVO t where userId=:userId") // 객체지향쿼리
    List<UserVO> findByUserIdLessThanSQL(@Param("userId") Long userId);

    List<UserVO> findByUserIdLessThanOrderByUserIdDesc(Long userId);

    @Query(value = "SELECT * FROM tbl_user WHERE username = ?1", nativeQuery = true)
    UserVO findByUserNameUserId(String user_name);

}

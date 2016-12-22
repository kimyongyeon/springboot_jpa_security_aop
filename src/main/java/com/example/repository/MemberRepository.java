package com.example.repository;

import com.example.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@Repository
public interface MemberRepository extends JpaRepository<UserVO, Long>{
}

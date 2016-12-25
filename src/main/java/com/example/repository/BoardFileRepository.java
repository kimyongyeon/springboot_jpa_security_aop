package com.example.repository;

import com.example.vo.BoardFileVO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yongyeon on 2016-12-25.
 */
public interface BoardFileRepository extends JpaRepository<BoardFileVO, Long> {
}

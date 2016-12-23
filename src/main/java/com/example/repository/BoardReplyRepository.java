package com.example.repository;

import com.example.vo.BoardReplyVO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kimyongyeon on 2016-12-23.
 */
public interface BoardReplyRepository extends JpaRepository<BoardReplyVO, Long> {
}

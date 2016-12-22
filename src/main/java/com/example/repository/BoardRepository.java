package com.example.repository;

import com.example.vo.BoardVO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kimyongyeon on 2016-12-23.
 * 페이징 처리를 위해서 PagingAndSortingRepository 상속이 달라 졌음.
 */
@Repository
public interface BoardRepository extends PagingAndSortingRepository<BoardVO, Long> {
    public List<BoardVO> findAllByOrderByTitleAsc();
}

package com.example.service;

import com.example.repository.BoardReplyRepository;
import com.example.repository.BoardRepository;
import com.example.vo.BoardReplyVO;
import com.example.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kimyongyeon on 2016-12-23.
 */
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardReplyRepository boardReplyRepository;

    /**
     * readOnly = true 일 경우 insert, update, delete 실행시 예외발생, 기본설정은 false
     * @param boardVO
     */
    @Transactional
    public void write(BoardVO boardVO) throws Exception{
        boardRepository.save(boardVO);
        // 트랜잭션 테스트를 위해서 강제로 익셉션을 발생한다.
        if(false){
            throw new NumberFormatException();
        }
    }

    @Transactional
    public void replyWrite(BoardReplyVO boardReplyVO) throws Exception{
        boardReplyRepository.save(boardReplyVO);
        // 트랜잭션 테스트를 위해서 강제로 익셉션을 발생한다.
        if(false){
            throw new NumberFormatException();
        }
    }

    public Map pageList(Pageable pageble) {
        Map returnMap = new HashMap();
        returnMap.put("items", boardRepository.findAll(pageble));
        return returnMap;
    }
}

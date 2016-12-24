package com.example.restcontroller;

import com.example.repository.MemberRepository;
import com.example.service.BoardService;
import com.example.vo.BoardReplyVO;
import com.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@RestController
public class TestJavaRestController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardService boardService;

    /**
     * json 문자열 출력
     * @return
     */
    @RequestMapping("/test")
    public String helloworld() {
        return "hello java test";
    }

    /**
     * 회원 정보 등록
     * @param userVO
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert(@ModelAttribute UserVO userVO) {
        memberRepository.save(userVO);
        return "success";
    }

    /**
     * 회원 정보 전체 출력
     * @return
     */
    @RequestMapping("/members")
    public Iterable<UserVO> members() {
        return memberRepository.findAll();
    }

    /**
     * 페이징 처리
     * @param pageable
     * @return
     */
    @RequestMapping("/pageList")
    public Map pageList(@PageableDefault(sort = { "bno" }, direction = Sort.Direction.DESC, size = 2) Pageable pageable) {
        Map map = boardService.pageList(pageable);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/boardReplyInsert")
    public String boardReplyInsert(@ModelAttribute BoardReplyVO boardReplyVO) throws Exception{
        boardService.replyWrite(boardReplyVO);
        return "success";
    }


}

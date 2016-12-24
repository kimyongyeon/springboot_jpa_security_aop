package com.example;

import com.example.config.StorageProperties;
import com.example.repository.MemberRepository;
import com.example.service.BoardService;
import com.example.service.MemberService;
import com.example.service.StorageService;
import com.example.vo.BoardVO;
import com.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 *  @EnableAspectJAutoProxy를 명시하면 Spring AOP를 사용하기 위한 첫 준비가 끝난다.
 @EnableAspectJAutoProxy은 XML 기반의 ApplicationContext 설정에서의 <aop:aspectj-autoproxy />와 동일한 기능을 한다.
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(StorageProperties.class)
public class SpringbootJpaSecurtyProjectApplication {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BoardService boardService;

	@Autowired
	MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaSecurtyProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			// repository, service 테스트
			UserVO userVO = new UserVO();
			userVO.setUser_name("user");
			userVO.setUserPasswd("1234");
			memberRepository.save(userVO);

			List list = memberService.getUserList();
			System.out.println("getUserList => " + list);

			memberService.logic();

			List<UserVO> userVOList = memberRepository.findByUserIdLessThanSQL(1L);
			userVOList.forEach(i-> System.out.println(i.getUserId()));

			UserVO userVO1 = memberRepository.findByUserName("user");
			System.out.println("findByUserName >> " + userVO1.getUser_name());

			UserVO userVO2 = memberRepository.findByUserNameUserId("user");
			System.out.println("findByUserNameUserId >> " + userVO2.getUser_name());

			List<UserVO> userVOList1 = memberService.findWithUserName("user");
			userVOList1.forEach(i-> System.out.println(i.getUserId()));

			try {
				BoardVO boardVO = new BoardVO();
				boardVO.setTitle("제목 입니다.");
				boardVO.setContent("내용 입니다.");
				boardService.write(boardVO);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
	}
}

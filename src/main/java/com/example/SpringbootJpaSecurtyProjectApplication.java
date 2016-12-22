package com.example;

import com.example.repository.BoardRepository;
import com.example.repository.MemberRepository;
import com.example.service.BoardService;
import com.example.service.MemberService;
import com.example.vo.BoardVO;
import com.example.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 *  @EnableAspectJAutoProxy를 명시하면 Spring AOP를 사용하기 위한 첫 준비가 끝난다.
 @EnableAspectJAutoProxy은 XML 기반의 ApplicationContext 설정에서의 <aop:aspectj-autoproxy />와 동일한 기능을 한다.
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringbootJpaSecurtyProjectApplication {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BoardService boardService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaSecurtyProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			UserVO userVO = new UserVO();
			userVO.setUser_name("user");
			userVO.setUserPasswd("1234");
			memberRepository.save(userVO);

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

package com.example

import com.example.repository.MemberRepository
import com.example.vo.UserVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * Created by kimyongyeon on 2016-12-21.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRespository extends Specification{

    def "로그인 처리가 제대로 되는지 테스트 해 보기"() {
        given:
        def memberRepository = Stub()
        def user = new UserVO(user_name: "kimyongyeon", userPasswd: "1234")

        when:
        memberRepository.save(user) >> user

        then:
        println user == memberRepository.findOne(1)

    }

    def "다양한 제곱 테스트"() {
        expect:
        Math.pow(base, 2) == expectedResult

        where:
        base || expectedResult
        2    || 4
        3    || 9
        10   || 100
    }

}

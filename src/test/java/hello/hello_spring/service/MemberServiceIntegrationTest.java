package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

  @Autowired
  MemberRepository memberRepository;
  @Autowired
  MemberService memberService;

  @Test
  public void 회원가입() throws Exception {
    Member member = new Member();
    member.setName("테스트입니다.");

    Long saveId = memberService.join(member);

    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

  }

  @Test
  public void 중복_회원_예외() throws Exception {
    Member member1 = new Member();
    member1.setName("spring1");

    Member member2 = new Member();
    member2.setName("spring1");

    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> {
      memberService.join(member2);
    });

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

  }

}
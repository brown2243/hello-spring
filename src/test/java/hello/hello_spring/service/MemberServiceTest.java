package hello.hello_spring.service;

import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;

public class MemberServiceTest {
  MemoryMemberRepository memberRepository;
  MemberService memberService;

  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  public void 회원가입() throws Exception {
    Member member = new Member();
    member.setName("hello");

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

    // try {
    // memberService.join(member2);
    // fail();
    // } catch (IllegalStateException e) {
    // assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    // }

  }

  // @Test
  // void findMembers() {
  // return memberRepository.findAll();
  // }

  // @Test
  // void findOne(Long id) {
  // return memberRepository.findById(id);
  // }
}
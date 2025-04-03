package hello.hello_spring.service;

import java.util.List;
import java.util.Optional;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

public class MemberService {
  private MemberRepository memberRepository = new MemoryMemberRepository();

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public Long join(Member member) {
    // Optional<Member> result = memberRepository.findByName(member.getName());
    validateDuplicateName(member);

    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateName(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원 입니다.");
        });
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long id) {
    return memberRepository.findById(id);
  }
}
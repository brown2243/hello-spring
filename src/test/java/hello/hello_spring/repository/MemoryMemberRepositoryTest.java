package hello.hello_spring.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hello_spring.domain.Member;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  public void AfterEach() {
    repository.clearStore();
  }

  @Test
  public void save() {
    Member member = new Member();
    member.setName("spring");

    repository.save(member);
    Member result = repository.findById(member.getId()).get();
    assertThat(member).isEqualTo(result);
  }

  @Test
  public void findById() {
    Member member1 = new Member();
    member1.setName("spring1");

    Member member2 = new Member();
    member2.setName("spring2");

    repository.save(member1);
    repository.save(member2);

    Member result1 = repository.findById(member1.getId()).get();
    assertThat(member1).isEqualTo(result1);

    List<Member> list = repository.findAll();
    assertThat(list.size()).isEqualTo(2);
  }
}

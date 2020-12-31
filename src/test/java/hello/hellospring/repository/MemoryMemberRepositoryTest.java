package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {           // 다른 데서 갖다 쓸 것이 아니기 때문에 public이 없어도 됨

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach                              // 메서드가 실행이 끝날 때마다 동작하는 콜백 메서드
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("daldalhada");

        repository.save(member);

        Member result = repository.findById(member.getId()).orElse(member);

        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Kim");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Park");
        repository.save(member2);

        Member result = repository.findByName("Kim").orElse(member1);

        assertThat(result).isEqualTo(member1);
        //assertThat(result).isEqualTo(member2);        // AssertionFailedError
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Kim");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Park");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        // assertThat(result.size()).isEqualTo(3);      // AssertionFailedError
    }
}

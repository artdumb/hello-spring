package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;


public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메소드가 실행될때 테스트 데이터가 입력되는데 메소드 끝나면 데이터 지우는 메소드
    @AfterEach
    public  void afterEach(){
        repository.clearStore();
    }

    //repository에서 구현한 Save를 테스트하는 코드
    @Test
    public void save(){
        //멤버 도메인 객체를 정의
        Member member = new Member();
        //멤버객체의 setter를 이용, name의 값을 설정
        member.setName("spring");
        //repository에서 구현한 save를 실행
        repository.save(member);
        //저장된
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    //이름으로 검색하는 경우에 대해 테스트
    @Test
    public void findByName() {
        //객체하나를 선언하고 초기화한다.
        Member member1 = new Member();
        //이름을 지정한다.
        member1.setName("spring1");
        //레포지토리의 save메소드를 이용하여 해당 객체를 저장한다.
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //findByName 메소드를 실행해본다.
        //sprint1이라는 이름으로 검색하여 값을 가져온다.
        Member result = repository.findByName("spring1").get();
        //레포지토리에서 가져온 멤버객체가 member1과 같은지를 확인한다.
        assertThat(result).isEqualTo(member1);
    }

    //모든객체를 찾는 메소드를 테스트
    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll();
        //then
        //결과의 크기가 2와 같으면 ture
        assertThat(result.size()).isEqualTo(2);
    }



}

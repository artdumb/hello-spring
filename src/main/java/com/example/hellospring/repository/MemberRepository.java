package com.example.hellospring.repository;
import com.example.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

//멤버의 데이터베이스에 접근하는 기능을 정의한다.
//CRUD를 어떻게 할 것인 지 정의해준다
//현재 데이터베이스는 정해지지않고 메모리 구현체이다
//데이터베이스가 변경될 수 있으므로 인터페이스로 정의 한다.
public interface MemberRepository {
    //멤버 객체에서 save 기능을 하는 추상메소드 리턴타입은 Member
    Member save(Member member);
    //id로 하나의 데이터를 조회하는 추상메소드 optional은 null값 처리를 위한 데이터형
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

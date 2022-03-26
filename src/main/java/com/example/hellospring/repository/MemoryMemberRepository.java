package com.example.hellospring.repository;

import java.util.*;
import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;


//메모리구현체의 멤버 리포지토리에 대한 클래스이다
public class MemoryMemberRepository implements MemberRepository {
    //키와 값의 대응관계에 대한 자료형인 hashMap을 데이터베이스의 형태로 사용
    //hashmap은 put, get, containsKey, remove, size, clear 메소드를 제공한다.
    private static Map<Long, Member> store = new HashMap<>();
    //id값은 설정하는 것이 아니라 자동으로 숫자가 올라가며 저장되는 것을 구현하기 위해 sequence 변수사용
    private static long sequence = 0L;

    //추상메소드 save에 대한 구현
    @Override
    public Member save(Member member) {
        //member를 파라미터로 save를 실행시
        //멤버에 id를 squence값에서 1을 더하여 저장
        member.setId(++sequence);
        //멤버의 id를 키로 객체를 값으로 저장
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        //optional을 이용해 null일 떄도 이용가능한 데이터형을 반환
        //id를 키로 갖는 값을 반 환한다.
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<Member> findAll() {

        //모든 데이터들의 값을 반환한다.
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        //name에 해당하는 모든 객체를 검색한다.
        //람다를 활용하여 데이터를 조회하는 자바8의 문법
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    public void clearStore() {
        store.clear();
    } }
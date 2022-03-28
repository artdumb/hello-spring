package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;
    public JpaMemberRepository(EntityManager em){
        this.em = em;

    }

    @Override
    public Member save(Member member) {
        //영속하다라는 뜻
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //pk기반이 아닌건 jpql을 작성
        List<Member> result = em.createQuery("select m from Member m where m.name = name",Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //객체를 대상으로 쿼리를 날림
        //m 앤티티 객체 자체를 select 한다.
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        return result;
    }


}

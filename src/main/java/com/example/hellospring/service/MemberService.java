package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    //레포지 토리를 가져온다.,
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //레포지토리를 가져올떄 매번 인스턴스가 새로 생성되어 다른 데이터베이스가 되어버릴수있다
    //따라서 생성자를 만들어 매번 같은 인스턴스로 데이터다훈다
    //이를 의존성 주입이라고한다.
    private final MemberRepository memberRepository;
    //메모리 멤버 레포지토리를 사용한다 현
    //컨트롤러와 서비스를 연결한다 의존성주입

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }




    //회원가입
    //비즈니스로직을 구현
    //같은 이름이 있으면 안된다는 비즈니스로직구현
    public Long join(Member member){
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복회원X
        Optional<Member> result = memberRepository.findByName(member.getName());
        //optional 형에 사용가능하다
        //중복회원일경우 오류
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


    //전체회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

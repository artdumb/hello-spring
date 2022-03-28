package com.example.hellospring;

import com.example.hellospring.repository.JpaMemberRepository;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import com.example.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //스프링컨테이너에 빈으로 등록한다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
    //서비스, 리포지토리 의존성


}

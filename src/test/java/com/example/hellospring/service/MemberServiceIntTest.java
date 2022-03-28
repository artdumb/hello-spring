package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@Transactional
class MemberServiceIntTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

   @Test
   @Commit
   public void 회원가입() throws Exception{
       Member member = new Member();
       member.setName("hello");
        //When
       Long saveId = memberService.join(member);
        //Then
       Member findMember = memberRepository.findById(saveId).get();
       assertEquals(member.getName(), findMember.getName());

   }



}
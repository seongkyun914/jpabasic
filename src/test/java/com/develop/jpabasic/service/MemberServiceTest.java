package com.develop.jpabasic.service;

import com.develop.jpabasic.domain.Member;
import com.develop.jpabasic.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional()
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("seongkyun");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(saveId));
    }
    
    @Test
    public void 중복_회원() throws Exception{
        //given
        Member memberA = new Member();
        Member memberB = new Member();
        memberA.setName("seongkyun");
        memberB.setName("seongkyun");
        //when
        memberService.join(memberA);
        assertThrows(IllegalStateException.class,
                ()->memberService.join(memberB),"이미 가입된회원");
        //then
        //fail("여기까지 오면 안됨");
    }

}
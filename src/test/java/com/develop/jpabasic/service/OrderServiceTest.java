package com.develop.jpabasic.service;

import com.develop.jpabasic.domain.Address;
import com.develop.jpabasic.domain.Member;
import com.develop.jpabasic.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository
    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();
        //when

        //then
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","거리","123-123"));
        em.persist(member);
        return member;
    }

}
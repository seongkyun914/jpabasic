package com.develop.jpabasic.repository;

import com.develop.jpabasic.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

//    public List<Order> findAll(OrderSearchh orderSearch) {}


}

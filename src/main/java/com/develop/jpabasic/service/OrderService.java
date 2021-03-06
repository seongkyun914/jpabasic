package com.develop.jpabasic.service;

import com.develop.jpabasic.domain.*;
import com.develop.jpabasic.domain.item.Item;
import com.develop.jpabasic.repository.ItemRepository;
import com.develop.jpabasic.repository.MemberRepository;
import com.develop.jpabasic.repository.OrderRepository;
import com.develop.jpabasic.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    //주문
    public Long order(Long memberId,Long itemId,int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생서
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        
        return order.getId();


    }

    /**
     * 주문 취소
     * @param orderId
     */
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order= orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }
    //검색

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }

}

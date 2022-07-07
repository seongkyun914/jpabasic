package com.develop.jpabasic.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.hibernate.engine.spi.IdentifierValue;


import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems= new ArrayList<>();


    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //[ORDER, CANCEL]

    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==/
    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems){
        Order order= new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem:orderItems) {
            order.addOrderItems(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;

    }

    public void cancel(){
        if(delivery.getDeliveryStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem:orderItems) {
            orderItem.cancel();

        }
    }

    //==조회 로직==//
    /**
     *
     */
    public int getTotalPrice(){
        int totalPrice =0;
        for(OrderItem orderItem:orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}

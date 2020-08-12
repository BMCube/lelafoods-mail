package edu.miu.lelafoods.mail.domain;

import java.util.List;

public class Cart {
    List<Order> orderList;

    public List<Order> getOrder() {
        return orderList;
    }

    public void setOrder(List<Order> order) {
        this.orderList = order;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private String orderStatus;

    @Override
    public String toString() {
        return "Cart{" +
                "orderList=" + orderList +
                '}';
    }

}

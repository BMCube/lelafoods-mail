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
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "orderList=" + orderList +
                '}';
    }

}

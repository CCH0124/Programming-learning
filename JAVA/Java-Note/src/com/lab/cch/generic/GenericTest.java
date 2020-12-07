package com.lab.cch.generic;

import java.util.ArrayList;
import java.util.List;

class Order<T> {
    String orderName;
    int orderId;
    T orderT;

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }
    public Order() {

    }
    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }
    public <E> List<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();
        for (E e : arr) {
            list.add(e);
        }
        return list;
    }

}

class SubOrder extends Order<Integer> {

}
class SubOrder1<T> extends Order<T> {

}
public class GenericTest {
    public static void main(String[] args) {
        Order<String> order1 = new Order<>("Order1", 1001, "orderAA");
        order1.setOrderT("AA:Hello");

        Integer[] integers = new Integer[]{1, 2,3,4,5};
        List<Integer> li = order1.copyFromArrayToList(integers);
        System.out.println(li);
        /**
         * 繼承時指名泛型類型
         */
        SubOrder sub1 = new SubOrder();
        sub1.setOrderT(123);

        SubOrder1<String> sub2 = new SubOrder1<>();
        sub2.setOrderT("order2...");
    }
}

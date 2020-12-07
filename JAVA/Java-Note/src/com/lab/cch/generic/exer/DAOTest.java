package com.lab.cch.generic.exer;

import java.util.List;

public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();
        dao.save("1001", new User(1001, 34, "kevin"));
        dao.save("1002", new User(1002, 28, "Mary"));
        dao.save("1003", new User(1003, 12, "Tom"));

        dao.save("1001", new User(1001, 22, "Fun"));

        List<User> list = dao.list();
        list.forEach(System.out::println);
    }
}

package com.example.task4;

import com.example.task4.DAO.UserDAO;

public class Test {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.housesMoreThan2000());
    }
}

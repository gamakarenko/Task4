package com.example.task4.DAO;

import com.example.task4.Configuration.HibernateUtil;
import com.example.task4.Entities.Address;
import com.example.task4.Entities.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Entity;

import java.util.List;

public class UserDAO {
    public void saveUser(Users users) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(users);
        transaction.commit();
        if (transaction != null) {
            transaction.rollback();
        }
    }

    public void updateUser(Users users) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(users);
        transaction.commit();
    }

    public void deleteUser(int userId) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Users users = session.get(Users.class, userId);
        if (users != null) {
            session.delete(users);
            System.out.println("User has been deleted");
        }
        transaction.commit();
    }

    public Users getUser(int id) {
        Transaction transaction = null;
        Users users = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        users = session.get(Users.class, id);
        transaction.commit();
        return users;
    }

    public List<Users> getAllUsers() {
        Transaction transaction;
        List<Users> usersList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        usersList = session.createNativeQuery("SELECT u FROM Users u", Users.class).getResultList();
        transaction.commit();
        return usersList;
    }

    public List<Integer> housesMoreThan2000(){
        List<Integer> list;
        Transaction transaction;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        list = session.createNativeQuery("SELECT house FROM Address WHERE house > 2000").getResultList();
        transaction.commit();
        return list;
    }

}

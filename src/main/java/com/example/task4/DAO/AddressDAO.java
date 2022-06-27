package com.example.task4.DAO;

import com.example.task4.Configuration.HibernateUtil;
import com.example.task4.Entities.Address;
import com.example.task4.Entities.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AddressDAO {
    public void saveAddress(Address address) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
        if (transaction != null) {
            transaction.rollback();
        }
    }

    public void updateAddress(Address address) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(address);
        transaction.commit();
    }

    public void deleteAddress(int addressId) {
        Transaction transaction;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Address address = session.get(Address.class, addressId);
        if (address != null) {
            session.delete(address);
            System.out.println("Address has been deleted");
        }
        transaction.commit();
    }

    public Address getAddress(int id) {
        Transaction transaction = null;
        Address address = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        address = session.get(Address.class, id);
        transaction.commit();
        return address;
    }

    public List<Address> getAllAddress() {
        Transaction transaction;
        List<Address> addressList;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        addressList = session.createQuery("from Address ", Address.class).getResultList();
        transaction.commit();
        return addressList;
    }

}


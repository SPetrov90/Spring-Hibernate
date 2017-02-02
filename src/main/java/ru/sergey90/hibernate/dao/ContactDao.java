package ru.sergey90.hibernate.dao;

import ru.sergey90.hibernate.entity.Contact;

import java.util.List;

/**
 * Created by Sergey on 02.02.2017.
 */
public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(int id);
    Contact save(Contact contact);
    void delete(Contact contact);
}

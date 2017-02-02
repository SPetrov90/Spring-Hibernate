package ru.sergey90.hibernate.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sergey90.hibernate.entity.Contact;

import java.util.List;

/**
 * Created by Sergey on 02.02.2017.
 * Implementation for ContactDao
 * with Hibernate queries
 */
@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
    private static final Logger LOG = LoggerFactory.getLogger(ContactDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public ContactDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from ru.sergey90.hibernate.entity.Contact c").list();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("Contact.findAllWithDetails").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(int id) {
        return (Contact) sessionFactory.getCurrentSession()
                .getNamedQuery("Contact.findById")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().save(contact);
        LOG.info("Contact saves with ID: ", contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        LOG.info("Delete contact with ID: ", contact.getId());
    }
}

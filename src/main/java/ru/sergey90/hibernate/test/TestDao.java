package ru.sergey90.hibernate.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sergey90.hibernate.config.AppConfig;
import ru.sergey90.hibernate.dao.ContactDao;
import ru.sergey90.hibernate.entity.Contact;
import ru.sergey90.hibernate.entity.ContactDetail;
import ru.sergey90.hibernate.entity.Hobby;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Sergey on 02.02.2017.
 * Class for testing ContactDao
 */
public class TestDao {
    private static ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private static ContactDao contactDao = context.getBean("contactDao", ContactDao.class);

    public static void main(String[] args) {
        listContact(contactDao.findAll());
        listContactWithDetails(contactDao.findAllWithDetail());
        contactInfo();
        Contact contact = addNewContact();
        listContactWithDetails(contactDao.findAllWithDetail());
        deleteContact(contact);
        listContact(contactDao.findAll());
    }

    private static void deleteContact(Contact contact) {
        System.out.println("---------------------------------");
        contactDao.delete(contact);
        System.out.println("Delete contact:" + contact);
        System.out.println("---------------------------------");
    }

    private static Contact addNewContact() {
        System.out.println("---------------------------------");
        Contact contactWithDetails = new Contact();
        contactWithDetails.setFirstName("Vladimir");
        contactWithDetails.setLastName("Kuzmin");
        contactWithDetails.setBirthDate(new Date(
                new GregorianCalendar(1955, 6, 31).getTime().getTime()));
        ContactDetail contactDetails1 = new ContactDetail();
        contactDetails1.setTelType("Home");
        contactDetails1.setTelNumber("5-55-55-55");
        contactWithDetails.addContactDetail(contactDetails1);

        ContactDetail contactDetails2 = new ContactDetail();
        contactDetails2.setTelType("Mobile");
        contactDetails2.setTelNumber("8-(911)-5-55-55-55");
        contactWithDetails.addContactDetail(contactDetails2);

        Contact addedContact = contactDao.save(contactWithDetails);
        System.out.println("Added new contact:" + contactWithDetails);
        return addedContact;
    }

    private static void contactInfo() {
        System.out.println("---------------------------------");
        System.out.println("Info of contact with ID=1:");
        Contact contact = contactDao.findById(1);
        System.out.println(contact);
        System.out.println("---------------------------------");
    }

    private static void listContactWithDetails(List<Contact> contactList) {
        System.out.println("---------------------------------");
        System.out.println("Contact list with details:");
        for (Contact contact: contactList){
            System.out.println(contact);
            if (contact.getContactDetails() != null){
                for (ContactDetail c: contact.getContactDetails()){
                    System.out.println(c);
                }
            }
            if (contact.getHobbies() != null){
                for (Hobby hobby: contact.getHobbies()){
                    System.out.println(hobby);
                }
            }
        }
        System.out.println("---------------------------------");
    }

    private static void listContact(List<Contact> contactList){
        System.out.println("---------------------------------");
        System.out.println("Contact list without details:");
        for (Contact contact: contactList){
            System.out.println(contact);
        }
        System.out.println("---------------------------------");
    }
}

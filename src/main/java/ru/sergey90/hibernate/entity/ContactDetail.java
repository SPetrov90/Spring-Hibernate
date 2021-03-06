package ru.sergey90.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sergey on 02.02.2017.
 */
@Entity
@Table(name = "contact_tel_detail")
public class ContactDetail implements Serializable {
    private int id;
    private int version;
    private String telType;
    private String telNumber;
    private Contact contact;

    public ContactDetail() {
    }

    public ContactDetail(String telType, String telNumber) {
        this.telType = telType;
        this.telNumber = telNumber;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "TEL_TYPE")
    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ContactDetail{" +
                "id=" + id +
                ", version=" + version +
                ", telType='" + telType + '\'' +
                ", telNumber='" + telNumber + '\'' +
                '}';
    }
}

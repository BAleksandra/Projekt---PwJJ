package com.projektpk.szukajpracy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Obiekt Klient - szukajacy pracy
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idCustomer;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "pesel")
    private int pesel;

    @Column(name = "phonenumber")
    private int phonenumber;

    @Column(name = "certificate")
    private boolean certificate;

    @Column(name = "course")
    private boolean course;

    @Column(name = "cv")
    private boolean cv;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User userCustomer;

    public Customer() {
    }

    public Customer(String firstName, String lastName,
                    String address, int pesel, int phonenumber, boolean certificate,
                    boolean course, boolean cv, User user_Customer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.pesel = pesel;
        this.phonenumber = phonenumber;
        this.certificate = certificate;
        this.course = course;
        this.cv = cv;
        this.userCustomer = user_Customer;
    }

    public Customer(String firstName, String lastName, String address, int pesel, int phonenumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.pesel = pesel;
        this.phonenumber = phonenumber;
        this.certificate = false;
        this.course = false;
        this.cv = false;
    }

    public Customer(String firstName, String lastName, int phonenumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phonenumber = phonenumber;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public boolean isCertificate() {
        return certificate;
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }

    public boolean isCourse() {
        return course;
    }

    public void setCourse(boolean course) {
        this.course = course;
    }

    public boolean isCv() {
        return cv;
    }

    public void setCv(boolean cv) {
        this.cv = cv;
    }

    public User getUser_Customer() {
        return userCustomer;
    }

    public void setUser_Customer(User user_Customer) {
        this.userCustomer = user_Customer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", pesel=" + pesel +
                ", phonenumber=" + phonenumber +
                ", certificate=" + certificate +
                ", course=" + course +
                ", cv=" + cv +
                '}';
    }
}

package com.projektpk.szukajpracy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Obiekt firma
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long NIP;

    @Column(name = "companyName")
    private String companyName;


    @Column(name = "address")
    private String address;


    @Column(name = "city")
    private String city;


    @Column(name = "mail")
    private String mail;


    @Column(name = "postalcode")
    private int postalcode;


    @Column(name = "REGON")
    private int REGON;


    @Column(name = "KRS")
    private int KRS;

    //user
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    private User usercompany;

    public Company() {
    }

    public Company(String companyName, String address, String city, String mail, int postalcode, int REGON, int KRS, User user_company) {
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.postalcode = postalcode;
        this.REGON = REGON;
        this.KRS = KRS;
        this.usercompany = user_company;
    }

    public Company(String companyName, String address, String city, String mail, int postalcode, int REGON, int KRS) {
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.postalcode = postalcode;
        this.REGON = REGON;
        this.KRS = KRS;
    }

    public Company(String companyName, String address, String city, String mail, int postalcode) {
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.mail = mail;
        this.postalcode = postalcode;
    }

    public long getNIP() {
        return NIP;
    }

    public void setNIP(long NIP) {
        this.NIP = NIP;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public int getREGON() {
        return REGON;
    }

    public void setREGON(int REGON) {
        this.REGON = REGON;
    }

    public int getKRS() {
        return KRS;
    }

    public void setKRS(int KRS) {
        this.KRS = KRS;
    }

    public User getUser_company() {
        return usercompany;
    }

    public void setUser_company(User user_company) {
        this.usercompany = user_company;
    }

    @Override
    public String toString() {
        return "Company{" +
                "NIP=" + NIP +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", mail=" + mail +
                ", postalcode=" + postalcode +
                ", REGON=" + REGON +
                ", KRS=" + KRS +
                '}';
    }
}

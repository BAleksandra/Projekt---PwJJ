package com.projektpk.szukajpracy.Model;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Mode Uzytkownika podstawowy
 */
@EnableJpaAuditing
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idUser;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @Column(name = "mail")
    private String mail;

    @Column(name = "usertype")
    private int usertype;

    @Column(name = "active")
    private boolean active;

    public User(){
    }

    public User(String password, String login, String mail, int usertype, boolean active) {
        this.password = password;
        this.login = login;
        this.mail = mail;
        this.usertype = usertype;
        this.active = active;
    }

    public User(String login, String password, String mail) {
        this.password = password;
        this.login = login;
        this.usertype = 1;
        this.active = false;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUsertype() {
        return this.usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                ", usertype=" + usertype +
                ", active=" + active +
                '}';
    }
}

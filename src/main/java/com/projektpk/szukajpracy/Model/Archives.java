package com.projektpk.szukajpracy.Model;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Obiekt archwium
 */
@Entity
@Table(name = "archives")
public class Archives implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idArchives;

    @Column(name = "title")
    private int title;

    @Column(name = "author")
    private String Author;

    @Column(name = "date")
    private java.util.Date Date;

    public Archives() {
    }

    public Archives(int title, String author, java.util.Date date) {
        this.title = title;
        Author = author;
        Date = date;
    }

    @Override
    public String toString() {
        return "Archives{" +
                "idArchives=" + idArchives +
                ", title=" + title +
                ", Author='" + Author + '\'' +
                ", Date=" + Date +
                '}';
    }

    public long getIdArchives() {
        return idArchives;
    }

    public void setIdArchives(long idArchives) {
        this.idArchives = idArchives;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}

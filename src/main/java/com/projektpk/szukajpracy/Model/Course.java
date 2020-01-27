package com.projektpk.szukajpracy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Obiekt Kurs
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idCourse;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    @JsonIgnore
    private Customer customerCurse;

    public Course( String title, String type, Customer customer_Curse) {
        this.title = title;
        this.type = type;
        this.customerCurse = customer_Curse;
    }

    public Course() {
    }

    public Course( String title, String type) {
        this.title = title;
        this.type = type;
    }

    public long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(long idCourse) {
        this.idCourse = idCourse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer_Curse() {
        return customerCurse;
    }

    public void setCustomer_Curse(Customer customer_Curse) {
        this.customerCurse = customer_Curse;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}


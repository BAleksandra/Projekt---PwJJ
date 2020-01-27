package com.projektpk.szukajpracy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Obiekt Certyfikat
 */
@Entity
@Table(name = "certificate")
public class Certificate implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idCertificate;

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
    private Customer customerCertficate;

    public Certificate() {
    }

    public Certificate( String title, String type, Customer customer_Certficate) {
        this.title = title;
        this.type = type;
        this.customerCertficate = customer_Certficate;
    }

    public Certificate( String title, String type) {
        this.title = title;
        this.type = type;
    }

    public long getIdCertificate() {
        return idCertificate;
    }

    public void setIdCertificate(long idCertificate) {
        this.idCertificate = idCertificate;
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

    public Customer getCustomer_Certficate() {
        return customerCertficate;
    }

    public void setCustomer_Certficate(Customer customer_Certficate) {
        this.customerCertficate = customer_Certficate;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "idCertificate=" + idCertificate +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}


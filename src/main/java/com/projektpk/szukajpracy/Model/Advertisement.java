package com.projektpk.szukajpracy.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *  Obiekt ogloszenie
 */
@Entity
@Table(name = "advertisement")
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idAdvertisement;

    @Column(name = "title")
    private String title;

    @Column(name = "industry")
    private String industry;

    @Column(name = "city")
    private String city;

    @Column(name = "salary")
    private int salary;

    @Column(name = "company")
    private String company;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "dateAdded")
    private LocalDate dateAdded;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "periodOfValidity")
    private LocalDate periodOfValidity;

    @Column(name = "contractType")
    private String contractType;

    @Column(name = "contents")
    private String contents;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "company_id",
            nullable = true
    )
    @JsonIgnore
    private Company companyAdvertisement;


    public Advertisement(String title, String industry, String city, int salary, String company,
                         CharSequence periodOfValidity, String contractType, Company company_Advertisement,
                         Search advertisement_Search) {
        this.title = title;
        this.industry = industry;
        this.city = city;
        this.salary = salary;
        this.company = company;
        this.dateAdded =  LocalDate.now();
        this.periodOfValidity = LocalDate.parse(periodOfValidity);;
        this.contractType = contractType;
        this.companyAdvertisement = company_Advertisement;

    }

    public Advertisement(String title, String industry, String city, int salary, String company,
                         String contractType, String contents) {
        this.title = title;
        this.industry = industry;
        this.city = city;
        this.salary = salary;
        this.company = company;
        this.dateAdded =LocalDate.now();
        this.periodOfValidity = LocalDate.now().plusDays(60);
        this.contractType = contractType;
        this.contents = contents;
    }

    public Advertisement(String title, String industry, String city, int salary, String company, CharSequence periodOfValidity,
                         String contractType, String contents) {
        this.title = title;
        this.industry = industry;
        this.city = city;
        this.salary = salary;
        this.company = company;
        this.dateAdded =LocalDate.now();
        this.periodOfValidity = LocalDate.parse(periodOfValidity);;
        this.contractType = contractType;
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Advertisement() {
    }

    public long getIdAdvertisement() {
        return idAdvertisement;
    }

    public void setIdAdvertisement(long idAdvertisement) {
        this.idAdvertisement = idAdvertisement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getPeriodOfValidity() {
        return periodOfValidity;
    }

    public void setPeriodOfValidity(LocalDate periodOfValidity) {
        this.periodOfValidity = periodOfValidity;
    }


    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }


    public Company getCompany_Advertisement() {
        return companyAdvertisement;
    }

    public void setCompany_Advertisement(Company company_Advertisement) {
        this.companyAdvertisement = company_Advertisement;
    }



    @Override
    public String toString() {
        return "Advertisement{" +
                "idAdvertisement=" + idAdvertisement +
                ", title='" + title + '\'' +
                ", industry='" + industry + '\'' +
                ", city='" + city + '\'' +
                ", salary=" + salary + '\'' +
                ", company='" + company + '\'' +
                ", dateAdded=" + dateAdded + '\'' +
                ", periodOfValidity=" + periodOfValidity + '\'' +
                ", contractType='" + contractType + '\'' +
                '}';
    }
}

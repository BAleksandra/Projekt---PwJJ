package com.projektpk.szukajpracy.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
/**
 * model cv
 */
@Entity
@Table(name = "CV")
public class CV implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idCV;

    @Column(name = "dane")
    private String dane;

    @Column(name = "education")
    private String education;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "skills")
    private String skills;

    @Column(name = "workExperience")
    private String workExperience;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer cv;

    public CV() {
    }

    public CV( String dane, String education, String hobbies, String skills, String workExperience) {
        this.dane = dane;
        this.education = education;
        this.hobbies = hobbies;
        this.skills = skills;
        this.workExperience = workExperience;
    }

    public CV(String education, String hobbies, String skills, String workExperience) {
        this.education = education;
        this.hobbies = hobbies;
        this.skills = skills;
        this.workExperience = workExperience;
    }

    public String getDane() {
        return dane;
    }

    public void setDane(String dane) {
        this.dane = dane;
    }

    public long getIdCV() {
        return idCV;
    }

    public void setIdCV(long idCV) {
        this.idCV = idCV;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public Customer getCv() {
        return cv;
    }

    public void setCv(Customer cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "CV{" +
                "idCV=" + idCV +
                ", education='" + education + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", skills='" + skills + '\'' +
                ", workExperience='" + workExperience + '\'' +
                '}';
    }
}


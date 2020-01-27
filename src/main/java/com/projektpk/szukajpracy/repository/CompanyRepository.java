package com.projektpk.szukajpracy.repository;

import com.projektpk.szukajpracy.Model.Company;
import com.projektpk.szukajpracy.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository <Company,Long> {
    Company findByUsercompany(User usercompany);
}

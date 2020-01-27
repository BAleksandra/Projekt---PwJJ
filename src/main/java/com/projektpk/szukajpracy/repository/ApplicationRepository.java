package com.projektpk.szukajpracy.repository;

import com.projektpk.szukajpracy.Model.Application;
import com.projektpk.szukajpracy.Model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application,Long> {
    List<Application> findByCustomersApplication(Customer CustomersApplication);
}

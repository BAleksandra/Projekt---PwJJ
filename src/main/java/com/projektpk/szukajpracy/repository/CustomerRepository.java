package com.projektpk.szukajpracy.repository;

import com.projektpk.szukajpracy.Model.Customer;
import com.projektpk.szukajpracy.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByUserCustomer( User UserCustomer);
    Customer findCustomerByUserCustomer(User UserCustomer);
}

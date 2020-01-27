package com.projektpk.szukajpracy.controller;


import com.projektpk.szukajpracy.Model.Customer;
import com.projektpk.szukajpracy.Model.User;
import com.projektpk.szukajpracy.repository.CustomerRepository;
import com.projektpk.szukajpracy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    UserRepository repositoryUser;

    /*
    pobranie wszystkich klientow
     */
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            repository.findAll().forEach(customers::add);

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    pobranie klienta przez id
     */
    @GetMapping("/customers/{idCustomer}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("idCustomer") long idCustomer) {
        Optional<Customer> cusotmerData = repository.findById(idCustomer);

        if (cusotmerData.isPresent()) {
            return new ResponseEntity<>(cusotmerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    pobnirae klienta przez idusera
     */
    @GetMapping("/customers/find/{idUser}")
    public ResponseEntity<Customer> getCustomerByIdUser(@PathVariable("idUser") long idUser) {
        Optional<User> userData = repositoryUser.findById(idUser);
       Customer cusotmerData = repository.findCustomerByUserCustomer(userData.get());

         if (cusotmerData != null) {
            return new ResponseEntity<>(cusotmerData, HttpStatus.OK);
        }else{
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }

    /*
    dodanie klienta
     */
    @PostMapping(value = "/customers/{idUser}")
    public ResponseEntity<Customer> postCustomer(@PathVariable("idUser") long idUser,@RequestBody Customer customer) {
        try {
            Optional<User> userData = repositoryUser.findById(idUser);
            Customer cust = new Customer(customer.getFirstName(), customer.getLastName(),
                    customer.getAddress(), customer.getPesel(), customer.getPhonenumber());
            cust.setUser_Customer(userData.get());
            Customer _customer = repository.save(cust);
            return new ResponseEntity<>(_customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie klienta
     */
    @DeleteMapping("/customers/{idCustomer}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("idCustomer") long idCustomer) {
        try {
            repository.deleteById(idCustomer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie wszytskich klientow
     */
    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAllCustomer() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*
    aktualizacja kleintow
     */
    @PutMapping("/users/{idCustomer}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("idCustomer") long idCustomer, @RequestBody Customer customer) {
        Optional<Customer> customerData = repository.findById(idCustomer);

        if (customerData.isPresent()) {
            Customer _customer = customerData.get();

            _customer.setAddress(customer.getAddress());
            _customer.setCertificate(customer.isCertificate());
            _customer.setCourse(customer.isCourse());
            //TODO sprawdz cv boolean
            _customer.setCv(customer.isCv());
            _customer.setFirstName(customer.getFirstName());
            _customer.setLastName(customer.getLastName());
            _customer.setPesel(customer.getPesel());
            _customer.setPhonenumber(customer.getPhonenumber());

            return new ResponseEntity<>(repository.save(_customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

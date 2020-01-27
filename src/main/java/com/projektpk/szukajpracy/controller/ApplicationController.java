package com.projektpk.szukajpracy.controller;

import com.projektpk.szukajpracy.Model.Advertisement;
import com.projektpk.szukajpracy.Model.Application;
import com.projektpk.szukajpracy.Model.Company;
import com.projektpk.szukajpracy.Model.Customer;
import com.projektpk.szukajpracy.repository.AdvertisementRepository;
import com.projektpk.szukajpracy.repository.ApplicationRepository;
import com.projektpk.szukajpracy.repository.CompanyRepository;
import com.projektpk.szukajpracy.repository.CustomerRepository;
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
public class ApplicationController {

    @Autowired
    ApplicationRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AdvertisementRepository advertisementRepository;

    /*
    pobranie wszystkich zgloszen
     */
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = new ArrayList<>();
        try {
            repository.findAll().forEach(applications::add);

            if (applications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(applications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
   pobnirae zgloszenia przez idCustomer
    */
    @GetMapping("/applications/find/{idCustomer}")
    public ResponseEntity  <List<Application>> getApplicationByIdCustomer(@PathVariable("idCustomer") long idCustomer) {
        Optional<Customer> userData = customerRepository.findById(idCustomer);
        List <Application> cusotmerData = repository.findByCustomersApplication(userData.get());
        if (cusotmerData != null) {
            return new ResponseEntity<>(cusotmerData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    pobranie zgloszen po id
     */
    @GetMapping("/applications/{idApplication}")
    public ResponseEntity<Application> getApplicationById(@PathVariable("idApplication") long idApplication) {
        Optional<Application> applicationData = repository.findById(idApplication);

        if (applicationData.isPresent()) {
            return new ResponseEntity<>(applicationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

/*
dodanie zgloszenia
 */
    @PostMapping(value = "/applications/{idCustomer}/{NIP}/{idAdvertisement}")
    public ResponseEntity<Application> postApplication(@PathVariable("idCustomer") long idCustomer,@PathVariable("NIP") long NIP,
                                                       @PathVariable("idAdvertisement") long idAdvertisement,@RequestBody Application application) {
        try {

            Optional<Customer> customerData = customerRepository.findById(idCustomer);
            Optional<Company> companyData = companyRepository.findById(NIP);
            Optional<Advertisement> advertisementData = advertisementRepository.findById(idAdvertisement);

            Application application_ = new Application(application.getFirstName(), application.getLastName(),
                    application.getMail(), application.getPhoneNumber(), application.getAddress());

            application_.setAdvertisement(advertisementData.get());
            application_.setCustomers_Application(customerData.get());
            application_.setCompany_Application(companyData.get());

            Application _user = repository.save(application_);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie zgloszenia
     */
    @DeleteMapping("/applications/{idApplication}")
    public ResponseEntity<HttpStatus> deleteApplication(@PathVariable("idApplication") long idApplication) {
        try {
            repository.deleteById(idApplication);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie wszytskich zgloszen
     */
    @DeleteMapping("/applications")
    public ResponseEntity<HttpStatus> deleteAllApplications() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

/*
aktualizacja
 */
    @PutMapping("/applications/{idApplication}")
    public ResponseEntity<Application> updateUser(@PathVariable("idApplication") long idApplication, @RequestBody Application application) {
        Optional<Application> applicationData = repository.findById(idApplication);

        if (applicationData.isPresent()) {
            Application _application = applicationData.get();
            _application.setAddress(application.getAddress());
            _application.setFirstName(application.getFirstName());
            _application.setLastName(application.getLastName());
            _application.setMail(application.getMail());
            _application.setPhoneNumber(application.getPhoneNumber());
            return new ResponseEntity<>(repository.save(_application), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


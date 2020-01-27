package com.projektpk.szukajpracy.controller;


import com.projektpk.szukajpracy.Model.CV;
import com.projektpk.szukajpracy.Model.Customer;
import com.projektpk.szukajpracy.repository.CVRepository;
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
public class CVController {

    @Autowired
    CVRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    /*
    pobranie wszytskich cv
     */
    @GetMapping("/cv")
    public ResponseEntity<List<CV>> getAllCV() {
        List<CV> cvs = new ArrayList<>();
        try {
            repository.findAll().forEach(cvs::add);

            if (cvs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cvs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    pobnirae cv przez idCustomer
     */
    @GetMapping("/cv/find/{idCustomer}")
    public ResponseEntity<CV> getCvByidCustomer(@PathVariable("idCustomer") long idCustomer) {
        Optional<Customer> userData = customerRepository.findById(idCustomer);
        CV cusotmerData = repository.findByCv(userData.get());

        if (cusotmerData != null) {
            return new ResponseEntity<>(cusotmerData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*
    pobranie cv prze id
     */
    @GetMapping("/cv/{idCV}")
    public ResponseEntity<CV> getCVById(@PathVariable("idCV") long idCV) {
        Optional<CV> cvData = repository.findById(idCV);

        if (cvData.isPresent()) {
            return new ResponseEntity<>(cvData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    dodanie cv
     */
    @PostMapping(value = "/cv/{idCustomer}")
    public ResponseEntity<CV> postCV(@PathVariable("idCustomer") long idCustomer,@RequestBody CV cv) {
        try {
            Optional<Customer> userData = customerRepository.findById(idCustomer);

            CV cv_ = new CV(cv.getDane(), cv.getEducation(), cv.getHobbies(), cv.getSkills(), cv.getWorkExperience());
            cv_.setCv(userData.get());

            CV _user = repository.save(cv_);

            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie cv
     */
    @DeleteMapping("/cv/{idCV}")
    public ResponseEntity<HttpStatus> deleteCV(@PathVariable("idCV") long idCV) {
        try {
            repository.deleteById(idCV);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie wszystkich
     */
    @DeleteMapping("/cv")
    public ResponseEntity<HttpStatus> deleteAllCV() {
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
    @PutMapping("/cv/{idCV}")
    public ResponseEntity<CV> updateCV(@PathVariable("idCV") long idCV, @RequestBody CV cv) {
        Optional<CV> cvData = repository.findById(idCV);

        if (cvData.isPresent()) {

            CV _cv = cvData.get();
            _cv.setEducation(cv.getEducation());
            _cv.setHobbies(cv.getHobbies());
            _cv.setSkills(cv.getSkills());
            _cv.setWorkExperience(cv.getWorkExperience());

            return new ResponseEntity<>(repository.save(_cv), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

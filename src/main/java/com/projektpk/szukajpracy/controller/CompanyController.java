package com.projektpk.szukajpracy.controller;


import com.projektpk.szukajpracy.Model.Company;
import com.projektpk.szukajpracy.Model.User;
import com.projektpk.szukajpracy.repository.CompanyRepository;
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
public class CompanyController {

    @Autowired
    CompanyRepository repository;

    @Autowired
    UserRepository repositoryUser;

    /*
    POBRANIE  wszytskich firm
     */
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            repository.findAll().forEach(companies::add);

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    pobranie poszczegolnej firmy
     */
    @GetMapping("/companies/{NIP}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("NIP") long NIP) {
        Optional<Company> userData = repository.findById(NIP);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    dodanie firmy
     */
        @PostMapping(value = "/companies/lok/{idUser}")
    public ResponseEntity<Company> postCompany(@PathVariable("idUser") long idUser,@RequestBody Company company) {
        try {
            Optional<User> userData = repositoryUser.findById(idUser);
            Company _company = new Company( company.getCompanyName(),
                    company.getAddress(), company.getCity(),company.getMail(), company.getPostalcode(), company.getREGON(),
                    company.getKRS() );
            _company.setUser_company(userData.get());
            Company _user = repository.save(_company);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie firmy
     */
    @DeleteMapping("/companies/{NIP}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("NIP") long NIP) {
        try {
            repository.deleteById(NIP);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie wszytskich firm
     */
    @DeleteMapping("/companies")
    public ResponseEntity<HttpStatus> deleteAllCompanies() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*
        pobnirae klienta przez idusera
         */
    @GetMapping("/companies/find/{idUser}")
    public ResponseEntity<Company> getCompanyByIdUser(@PathVariable("idUser") long idUser) {
        Optional<User> userData = repositoryUser.findById(idUser);
        Company cusotmerData = repository.findByUsercompany(userData.get());

        if (cusotmerData != null) {
            return new ResponseEntity<>(cusotmerData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
/*
aktualizacja
 */
    @PutMapping("/companies/{NIP}")
    public ResponseEntity<Company> updateCompany(@PathVariable("NIP") long NIP, @RequestBody Company company) {
        Optional<Company> companyData = repository.findById(NIP);

        if (companyData.isPresent()) {
            Company _company = companyData.get();
            _company.setAddress(company.getAddress());
            _company.setCity(company.getCity());
            _company.setCompanyName(company.getCompanyName());
            _company.setMail(company.getMail());
            _company.setREGON(company.getREGON());
            _company.setKRS(company.getKRS());
            _company.setPostalcode(company.getPostalcode());
            _company.setUser_company(company.getUser_company());
            return new ResponseEntity<>(repository.save(_company), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

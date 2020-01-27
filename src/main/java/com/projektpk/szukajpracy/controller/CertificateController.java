package com.projektpk.szukajpracy.controller;

import com.projektpk.szukajpracy.Model.Certificate;
import com.projektpk.szukajpracy.Model.Customer;
import com.projektpk.szukajpracy.repository.CertificateRepository;
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
public class CertificateController {

    @Autowired
    CertificateRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    /*
    pobranie wszytskich certyfikatów
     */
    @GetMapping("/certificate")
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        List<Certificate> certificate = new ArrayList<>();
        try {
            repository.findAll().forEach(certificate::add);

            if (certificate.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    pobranie przez id
     */
    @GetMapping("/certificate/{idCertificate}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable("idCertificate") long idCertificate) {
        Optional<Certificate> certificateData = repository.findById(idCertificate);

        if (certificateData.isPresent()) {
            return new ResponseEntity<>(certificateData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

/*
dodanie certyfikatu
 */
    @PostMapping(value = "/certificate/{idCustomer}")
    public ResponseEntity<Certificate> postCertificate(@PathVariable("idCustomer") long idCustomer,@RequestBody Certificate certificate) {
        try {
            Optional<Customer> userData = customerRepository.findById(idCustomer);
            Certificate certificate_ = new Certificate( certificate.getTitle(),
                    certificate.getType());
            certificate_.setCustomer_Certficate(userData.get());
            Certificate _certificate = repository.save(certificate_);
            return new ResponseEntity<>(_certificate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie certyfiaktu
     */
    @DeleteMapping("/certificate/{idCertificate}")
    public ResponseEntity<HttpStatus> deleteCertificate(@PathVariable("idCertificate") long idCertificate) {
        try {
            repository.deleteById(idCertificate);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie wszytskich
     */
    @DeleteMapping("/certificate")
    public ResponseEntity<HttpStatus> deleteAllCertificates() {
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
    @PutMapping("/certificate/{idCertificate}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable("idCertificate") long idCertificate, @RequestBody Certificate certificate) {
        Optional<Certificate> certificateData = repository.findById(idCertificate);

        if (certificateData.isPresent()) {
            Certificate _certificate = certificateData.get();
            _certificate.setTitle(certificate.getTitle());
            _certificate.setType(certificate.getType());
            return new ResponseEntity<>(repository.save(_certificate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

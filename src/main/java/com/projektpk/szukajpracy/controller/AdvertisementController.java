package com.projektpk.szukajpracy.controller;


import com.projektpk.szukajpracy.Model.Advertisement;
import com.projektpk.szukajpracy.Model.Company;
import com.projektpk.szukajpracy.repository.AdvertisementRepository;
import com.projektpk.szukajpracy.repository.CompanyRepository;
import com.projektpk.szukajpracy.repository.SearchRepository;
import com.projektpk.szukajpracy.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    @Autowired
    AdvertisementRepository repository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    SearchRepository searchRepository;

    /*
    pobranie wszystkich ogloszen
     */
    @GetMapping()
    public ResponseEntity<List<Advertisement>> getAllAdvertisements() {
        List<Advertisement> advertisement = new ArrayList<>();
        try {
            repository.findAll().forEach(advertisement::add);

            if (advertisement.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(advertisement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*
znalezienie po typie
 */
    @GetMapping(value = "/findsalary/{salary}")
    public ResponseEntity<List<Advertisement>> findBySalary(@PathVariable int salary) {
        try {
            salary=salary-1;
            List<Advertisement> users = repository.findBySalaryIsGreaterThan(salary);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
   wyszukiwanie reklam przez miasto i branze
    */
    @GetMapping(value = "/findIndustrycity/{Industry}/{city}")
    public ResponseEntity<List<Advertisement>> findByCityAndIndustry(@PathVariable String Industry,
                                                                     @PathVariable String city ) {
        try {
            List<Advertisement> users = repository.findByIndustryAndCity(Industry, city);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



    /*
   wyszukiwanie reklam przez miasto i pensje
    */
    @GetMapping(value = "/findcitysalary/{city}/{salary}")
    public ResponseEntity<List<Advertisement>> findByCityAndSalary(@PathVariable String city,
                                                                   @PathVariable int salary ) {
        try {
            salary=salary-1;
            List<Advertisement> users = repository.findByCityAndSalaryIsGreaterThan(city, salary);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
  wyszukiwanie reklam przez miasto i pensje i branze
   */
    @GetMapping(value = "/findIndustrysalarycity/{Industry}/{salary}/{city}")
    public ResponseEntity<List<Advertisement>> findByIndustryAndCityAndSalary(@PathVariable String Industry,
                                                                              @PathVariable int salary, @PathVariable String city ) {
        try {
            salary=salary-1;
            List<Advertisement> users = repository.findByIndustryAndCityAndSalaryIsGreaterThan(Industry, city, salary );

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
   wyszukiwanie reklam przez branze i pensje
    */
    @GetMapping(value = "/findIndustrysalary/{Industry}/{salary}")
    public ResponseEntity<List<Advertisement>> findByIndustryAndSalary(@PathVariable String Industry,
                                                                       @PathVariable int salary ) {
        try {
            salary=salary-1;
            List<Advertisement> users = repository.findByIndustryAndSalaryIsGreaterThan(Industry, salary);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
  wyszukiwanie reklam przez miasto
   */
    @GetMapping(value = "/findcity/{city}")
    public ResponseEntity<List<Advertisement>> findByCity(@PathVariable String city) {
        try {
            List<Advertisement> users = repository.findByCity(city);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
  wyszukiwanie reklam przez Industry
   */
    @GetMapping(value = "/findIndustry/{Industry}")
    public ResponseEntity<List<Advertisement>> findByIndustry(@PathVariable String Industry) {
        try {
            List<Advertisement> users = repository.findByIndustry(Industry);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    znalezienie po id
     */
    @GetMapping("/{idAdvertisement}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable("idAdvertisement") long idAdvertisement) {
        Optional<Advertisement> advertisementData = repository.findById(idAdvertisement);

        if (advertisementData.isPresent()) {
            return new ResponseEntity<>(advertisementData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

/*
dodanie ogloszenia
 */
    @PostMapping(value = "/{nip}")
    public ResponseEntity<Advertisement> postAdvertisment( @PathVariable("nip") long nip,
                                                           @RequestBody Advertisement advertisement) {
        try {
            Optional<Company> companyData = companyRepository.findById(nip);

            /*
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
            LocalDate period = advertisement.getPeriodOfValidity();
            CharSequence datecharSequence = period.format(formatter);
*/
            Advertisement advertisement_ = new Advertisement(advertisement.getTitle(),
                    advertisement.getIndustry(), advertisement.getCity(), advertisement.getSalary(),
                    advertisement.getCompany() ,
                    advertisement.getContractType(), advertisement.getContents());

            advertisement_.setCompany_Advertisement(companyData.get());

            Advertisement _advertisement = repository.save(advertisement_);

            return new ResponseEntity<>(_advertisement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
 pobnirae cv przez idCustomer
  */
    @GetMapping("/find/{nip}")
    public ResponseEntity<Advertisement> getAdvertisementBynip(@PathVariable("nip") long nip) {
        Optional<Company> userData = companyRepository.findById(nip);
        Advertisement cusotmerData = repository.findBycompanyAdvertisement(userData.get());

        if (cusotmerData != null) {
            return new ResponseEntity<>(cusotmerData, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    usuniecie ogloszenia
     */
    @DeleteMapping("/{idAdvertisement}")
    public ResponseEntity<HttpStatus> deleteAdvertisment(@PathVariable("idAdvertisement") long idAdvertisement) {
        try {
            repository.deleteById(idAdvertisement);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuniecie wszystkich ogloszen
     */
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllAdvertisements() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*
aktualizacja ogloszenia
     */
    @PutMapping("/{idAdvertisement}")
    public ResponseEntity<Advertisement> updateAdvertisement(@PathVariable("idAdvertisement") long idAdvertisement,
                                                             @RequestBody Advertisement advertisement) {
        Optional<Advertisement> advertisementData = repository.findById(idAdvertisement);

        if (advertisementData.isPresent()) {

            Advertisement _advertisement = advertisementData.get();
           _advertisement.setCity(advertisement.getCity());
            _advertisement.setCompany(advertisement.getCompany());
            _advertisement.setContractType(advertisement.getContractType());
            _advertisement.setIndustry(advertisement.getIndustry());
            _advertisement.setPeriodOfValidity(advertisement.getPeriodOfValidity());
            _advertisement.setSalary(advertisement.getSalary());
            _advertisement.setTitle(advertisement.getTitle());

            return new ResponseEntity<>(repository.save(_advertisement), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

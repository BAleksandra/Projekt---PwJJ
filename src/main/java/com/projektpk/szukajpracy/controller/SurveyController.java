package com.projektpk.szukajpracy.controller;

import com.projektpk.szukajpracy.Model.Company;
import com.projektpk.szukajpracy.Model.Survey;
import com.projektpk.szukajpracy.repository.CompanyRepository;
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
@RequestMapping("/api/")
public class SurveyController {

    @Autowired
    SurveyRepository repository;

    @Autowired
    CompanyRepository companyRepository;

    /*
    pozuskanie wszystkich ankiet
     */
    @GetMapping("/survey")
    public ResponseEntity<List<Survey>> getAllSurvey() {
        List<Survey> survey = new ArrayList<>();
        try {
            repository.findAll().forEach(survey::add);

            if (survey.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


/*
wyszukanie ankiety po id
 */
    @GetMapping("/survey/{idSurvey}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable("idSurvey") long idSurvey) {
        Optional<Survey> surveyData = repository.findById(idSurvey);

        if (surveyData.isPresent()) {
            return new ResponseEntity<>(surveyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

/*
dodanie ankiety
 */
    @PostMapping(value = "/survey/{NIP}")
    public ResponseEntity<Survey> postSurvey(@PathVariable("NIP") long NIP,@RequestBody Survey survey) {
        try {
            Optional<Company> companyData = companyRepository.findById(NIP);
            Survey surver_ = new Survey(survey.getTitle(), survey.getNumberofquestions());
            surver_.setSurvey(companyData.get());
            Survey _survey = repository.save(surver_);
            return new ResponseEntity<>(_survey, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuwanie ankiety
     */
    @DeleteMapping("/survey/{idSurvey}")
    public ResponseEntity<HttpStatus> deleteSurvey(@PathVariable("idSurvey") long idSurvey) {
        try {

            repository.deleteById(idSurvey);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuwanie wszystkich
     */
    @DeleteMapping("/survey")
    public ResponseEntity<HttpStatus> deleteAllSurvey() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

   /*
   szukanie po tytule
    */
    @GetMapping(value = "survey/{title}")
    public ResponseEntity<List<Survey>> findBytitle(@PathVariable String title) {
        try {
            List<Survey> survey = repository.findByTitle(title);

            if (survey.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(survey, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    anktualizacja
     */
    @PutMapping("/survey/{idSurvey}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable("idSurvey") long idSurvey, @RequestBody Survey survey) {
        Optional<Survey> surveyData = repository.findById(idSurvey);

        if (surveyData.isPresent()) {
            Survey _survey = surveyData.get();

            return new ResponseEntity<>(repository.save(_survey), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

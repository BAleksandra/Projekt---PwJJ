package com.projektpk.szukajpracy.repository;

import com.projektpk.szukajpracy.Model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SurveyRepository extends CrudRepository<Survey,Long> {

    List<Survey> findByTitle(String mail);
}

package com.projektpk.szukajpracy.repository;

import com.projektpk.szukajpracy.Model.*;
import org.springframework.data.repository.CrudRepository;

public interface CVRepository extends CrudRepository<CV,Long> {
    CV findByCv (Customer Cv);
}

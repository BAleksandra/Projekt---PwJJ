package com.projektpk.szukajpracy.repository;

import com.projektpk.szukajpracy.Model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SearchRepository extends CrudRepository<Search,Long> {

    List<Search> findByIndustry(String Industry);
    List<Search> findBySpecyfication(String Specyfication);
    List<Search> findByPosition(String Position);

    List<Search> findByIndustryAndPosition(String Industry, String Position);
    List<Search> findBySpecyficationAndIndustry(String Specyfication, String Industry);
    List<Search> findByPositionAndSpecyfication(String Position, String Specyfication);

    List<Search> findByIndustryAndPositionAndSpecyfication(String Industry,String Position, String Specyfication);

}

package com.projektpk.szukajpracy.repository;


import com.projektpk.szukajpracy.Model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdvertisementRepository extends CrudRepository<Advertisement,Long> {
    List<Advertisement> findByTitle(String Title);
    List<Advertisement> findBySalary(int Salary);
    List<Advertisement> findByCity(String City);
    List<Advertisement> findByContractType(String ConstractType);
    List<Advertisement> findByIndustry(String Insudtry);
   Advertisement findBycompanyAdvertisement(Company companyAdvertisement);
    List<Advertisement> findByIndustryAndCity(String Insudtry, String City);



    List<Advertisement> findByCityAndSalaryIsGreaterThan(String City, int Salary);
    List<Advertisement> findByIndustryAndSalaryIsGreaterThan(String Insudtry, int Salary);


    List<Advertisement> findByIndustryAndSalaryAndCity(String Insudtry, int Salary, String City);



    List<Advertisement> findBySalaryIsGreaterThan(int Salary);
    List<Advertisement> findByIndustryAndCityAndSalaryIsGreaterThan(String Insudtry,String City, int Salary);

}

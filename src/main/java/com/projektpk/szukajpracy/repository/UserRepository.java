package com.projektpk.szukajpracy.repository;
import com.projektpk.szukajpracy.Model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User,Long>{
    List<User> findByusertype(int usertype);
    List<User> findBymail(String mail);
    User findByLogin(String login);
}

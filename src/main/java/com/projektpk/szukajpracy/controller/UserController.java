package com.projektpk.szukajpracy.controller;

import com.projektpk.szukajpracy.Model.User;
import com.projektpk.szukajpracy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//mapping for restful request
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository repository;

    /*
    pozyskanie wszystkich uzytkownikow
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            repository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    znalezene uzytkownika
     */
    @GetMapping("/users/{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable("idUser") long idUser) {
        Optional<User> userData = repository.findById(idUser);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    wyszukanie uzytkownika
     */
    @GetMapping("/users/login/{login}")
    public ResponseEntity<User> getUserBylogin(@PathVariable("login") String login) {

            User userData = repository.findByLogin(login);

            if (userData != null) {
                return new ResponseEntity<>(userData, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

/*
dodanie uzytkownika
 */
    @PostMapping(value = "/users")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        try {
            User _user = repository.save(new User(user.getLogin(), user.getPassword(), user.getMail()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    usuwanie uzytkownika
     */
    @DeleteMapping("/users/{idUser}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("idUser") long idUser) {
        try {
            repository.deleteById(idUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    wszyscy uzytkownicy
     */
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    /*
    wyszukiwanie uzytkownika przez typ
     */
    @GetMapping(value = "users/usertype/{usertype}")
    public ResponseEntity<List<User>> findByusertype(@PathVariable int usertype) {
        try {
            List<User> users = repository.findByusertype(usertype);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    wyszukiwanie uzytkownika przez maila
     */
    @GetMapping(value = "users/mail/{mail}")
    public ResponseEntity<List<User>> findBymail(@PathVariable String mail) {
        try {
            List<User> users = repository.findBymail(mail);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /*
    odsiwezenie uzytkownika
     */
    @PutMapping("/users/{idUser}")
    public ResponseEntity<User> updateUser(@PathVariable("idUser") long idUser, @RequestBody User user) {
        Optional<User> userData = repository.findById(idUser);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setLogin(user.getLogin());
            _user.setPassword(user.getPassword());
            _user.setMail(user.getMail());
            _user.setUsertype(user.getUsertype());
            _user.setActive(user.isActive());
            return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

package com.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.beans.User;
import com.example.demo.model.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class FirstRest {

    @Autowired
    private UserServiceImpl service;

    @PostMapping
    public User store(@RequestBody User user){
        User createdUser = service.storeUser(user);
        return createdUser;
    }
    @GetMapping
    public List<User> fetchUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> fetchUser(@PathVariable("userId") int id) {
        Map<String, String> map = new HashMap<String, String>();
        User user = service.getUser(id);
        if(user == null) {
            map.put("error", "id is invalid: "+id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @PutMapping("/{userId}/{dob}")
    public ResponseEntity<Object> updateUserDob(@PathVariable("userId") int id, @PathVariable("dob") String dob){
        Map<String, String> map = new HashMap<String, String>();
        User user = service.getUser(id);
        LocalDate newDate;
        try{
            newDate = LocalDate.parse(dob);
        }catch (Exception e){
            map.put("error", "date format is invalid: "+dob);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        if(user == null) {
            map.put("error", "id is invalid: "+id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        } else {
            service.updateUserDob(id, newDate);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> removeUser(@PathVariable("userId") int id) {
        Map<String, String> map = new HashMap<String, String>();
        User user = service.getUser(id);
        if(!service.deleteUser(id)){
            map.put("error", "id is invalid: "+id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        map.put("message", "user with id "+id+ " has been removed");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}

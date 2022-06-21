package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/first")
public class FirstRest {

    @GetMapping
    public String greetings() {
        return "Welcome to REST";
    }

    @GetMapping("/hello")
    public Map<String, String> getJson() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "Alexander");
        map.put("email", "alexiscool@gmail.fake");
        return map;
    }

    @PostMapping("/hello")
    public Map<String, String> postJson(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", "hello post");
        return map;
    }

    @DeleteMapping("/hello")
    public Map<String, String> deleteJson(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", "hello delete");
        return map;
    }

    @PutMapping("/hello")
    public Map<String, String> putJson(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", "hello put");
        return map;
    }

}

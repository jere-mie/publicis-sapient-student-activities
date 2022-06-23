package com.example.springbootdbapp.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.springbootdbapp.exceptions.FriendNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springbootdbapp.exceptions.ProfileNotFoundException;
import com.example.springbootdbapp.model.beans.Friend;
import com.example.springbootdbapp.model.beans.Profile;
import com.example.springbootdbapp.model.service.FriendService;
import com.example.springbootdbapp.model.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private FriendService friendService;

    @PostMapping
    public ResponseEntity<Object> store(@RequestBody Profile profile) { // profileId, name, dob, phone
        Profile storedProfile = profileService.storeProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(storedProfile);
    }
    @GetMapping
    public ResponseEntity<Object> getProfiles() {
        List<Profile> list = profileService.fetchProfiles();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable("id") int id) {
        try {
            Profile profile = profileService.fetchProfile(id);
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        } catch (ProfileNotFoundException e) {
            // Exception has getMessage() that returns the message initialized when exception occurs
            Map<String, String> map = new HashMap<String, String>();
            map.put("error", e.getMessage()); // e.getMessage(): Profile with an id not found
            map.put("status", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }
    @PostMapping("/{profileId}")
    public ResponseEntity<Object> addFriend(@RequestBody Friend friend,
                                            @PathVariable("profileId") int id) {
        Friend createdFriend = friendService.addFriend(id, friend);
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", "Added "+createdFriend.getName());
        map.put("description", "Added to profile id: "+createdFriend.getProfileIdRef());
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @PutMapping("/{profileId}")
    public ResponseEntity<Object> updateProfileDob(@RequestBody LocalDate dob,
                                                   @PathVariable("profileId") int id){
        try{
            Profile profile = profileService.updateProfilePhone(id, dob);
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        }catch(ProfileNotFoundException e){
            Map<String, String> map = new HashMap<String, String>();
            map.put("error", e.getMessage()); // e.getMessage(): Profile with an id not found
            map.put("status", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @DeleteMapping("/{friendId}")
    public ResponseEntity<Object> deleteFriendFromProfile(@PathVariable("friendId") int friendId){
        try{
            friendService.deleteFriend(friendId);
            Map<String, String> map = new HashMap<String, String>();
            map.put("message", "successfully deleted friend "+friendId); // e.getMessage(): Profile with an id not found
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }catch(FriendNotFoundException e){
            Map<String, String> map = new HashMap<String, String>();
            map.put("error", e.getMessage()); // e.getMessage(): Profile with an id not found
            map.put("status", "404");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PutMapping("/{profileId}/{friendId}")
    public ResponseEntity<Object> updateFriendPhone(@RequestBody long phone,
                                                    @PathVariable("profileId") int profileId,
                                                    @PathVariable("friendId") int friendId){
        try{
            Friend friend = friendService.updateFriendPhone(friendId, profileId, phone);
            return ResponseEntity.status(HttpStatus.OK).body(friend);
        }catch(Exception e){
            Map<String, String> map = new HashMap<String, String>();
            map.put("error", e.getMessage()); // e.getMessage(): Profile with an id not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }
}
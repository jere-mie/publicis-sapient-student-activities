package com.example.demo.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.beans.User;

@Service
public class UserServiceImpl {
    private static List<User> usersList = new ArrayList<User>();
    private static int idCounter = 1;
    public User storeUser(User user) {
        user.setUserId(idCounter++);
        usersList.add(user);
        return user;
    }
    public List<User> getAllUsers() {
        return usersList;
    }

    public User getUser(int id){
        for(int i=0; i<usersList.size(); i++){
            if(usersList.get(i).getUserId() == id){
                return usersList.get(i);
            }
        }
        return null;
    }

    public Boolean updateUserDob(int id, LocalDate newDob){
        User userToUpdate = getUser(id);
        if(userToUpdate == null){
            return false;
        }
        userToUpdate.setDob(newDob);
        return true;
    }

    public Boolean deleteUser(int id){
        User userToDelete = getUser(id);
        if(userToDelete == null){
            return false;
        }
        usersList.remove(userToDelete);
        return true;
    }
}

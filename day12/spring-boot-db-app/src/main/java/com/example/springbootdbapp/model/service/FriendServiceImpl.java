package com.example.springbootdbapp.model.service;

import com.example.springbootdbapp.exceptions.FriendNotFoundException;
import com.example.springbootdbapp.exceptions.UnauthorizedProfileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootdbapp.model.beans.Friend;
import com.example.springbootdbapp.model.beans.Profile;
import com.example.springbootdbapp.model.dao.FriendRepository;
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendDao;

    @Override
    @Transactional
    public Friend addFriend(int profileIdRef, Friend friend) {
        // to make it simple we will not enter profile id that doesn't exist in the Profile
        friend.setProfileIdRef(profileIdRef);
        return friendDao.save(friend);
    }

    @Override
    @Transactional
    public void deleteFriend(int friendId) throws FriendNotFoundException {
        Friend friend = friendDao.findById(friendId).orElse(null);
        if(friend == null){
            throw new FriendNotFoundException("Friend with an id: "+friendId+" is not found!");
        }
        friendDao.delete(friend);
    }

    @Override
    @Transactional
    public Friend updateFriendPhone(int friendId, int profileId, long phone) throws FriendNotFoundException, UnauthorizedProfileException{
        Friend friend = friendDao.findById(friendId).orElse(null);
        if(friend == null){
            throw new FriendNotFoundException("Friend with an id: "+friendId+" is not found!");
        }
        if(friend.getProfileIdRef() != profileId){
            throw new UnauthorizedProfileException("Profile with id "+profileId+" is not authorized to update friend "+friendId);
        }
        friend.setPhone(phone);
        return friendDao.save(friend);
    }
}
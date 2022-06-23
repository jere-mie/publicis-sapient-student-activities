package com.example.springbootdbapp.model.service;

import com.example.springbootdbapp.exceptions.FriendNotFoundException;
import com.example.springbootdbapp.exceptions.UnauthorizedProfileException;
import com.example.springbootdbapp.model.beans.Friend;
import com.example.springbootdbapp.model.beans.Profile;

public interface FriendService {
    // since Friend table must have profileIdRef we will pass profile id while adding a friend
    public Friend addFriend(int profileIdRef, Friend friend);
    public void deleteFriend(int friendId) throws FriendNotFoundException;
    public Friend updateFriendPhone(int friendId, int profileId, long phone) throws FriendNotFoundException, UnauthorizedProfileException;
}
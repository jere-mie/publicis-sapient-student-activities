package com.example.springbootdbapp.model.service;

import java.time.LocalDate;
import java.util.List;

import com.example.springbootdbapp.exceptions.ProfileNotFoundException;
import com.example.springbootdbapp.model.beans.Profile;

/*
 * This is an interface for business layer, it needs to be implemented by developer
 */
public interface ProfileService {
    // this must store profile & return created profile
    public Profile storeProfile(Profile profile);
    // this must return all the profiles in the List<Profile>
    public List<Profile> fetchProfiles();
    // this must return Profile or throw ProfileNotFoundException
    public Profile fetchProfile(int id) throws ProfileNotFoundException;
    public Profile updateProfilePhone(int id, LocalDate dob) throws ProfileNotFoundException;
}

package com.rei1997.vault.model.repository;

import java.util.Optional;

import com.rei1997.vault.model.entity.UserProfile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserProfileRepo extends MongoRepository<UserProfile,String>{

    Optional<UserProfile> findUserProfileByEmail(String email);

    @Query(value = "{ 'email' :  ?0 }", fields = "{ 'gender':1 }")
    Optional<Integer> findGenderByEmail(String email);
    
    @Query(value = "{ 'email' :  ?0 }", fields = "{ 'emailBak':1 }")
    Optional<String> findEmailBakByEmail(String email);
    
}

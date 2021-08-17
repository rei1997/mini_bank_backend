package com.rei1997.vault.model.repository;

import java.util.Optional;

import com.rei1997.vault.model.entity.UserProfile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepo extends MongoRepository<UserProfile,String>{

    Optional<UserProfile> findUserProfileByEmail(String email);
    Optional<Integer> findGenderByEmail(String email);
    Optional<String> findEmailBakByEmail(String email);
    
}

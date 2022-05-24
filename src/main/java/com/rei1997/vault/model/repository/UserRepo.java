package com.rei1997.vault.model.repository;

import java.util.Optional;
import com.rei1997.vault.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String>{

    Optional<User>findUserByEmail(String email);

}

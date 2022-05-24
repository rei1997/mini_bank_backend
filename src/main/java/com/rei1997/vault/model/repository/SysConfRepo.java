package com.rei1997.vault.model.repository;

import com.rei1997.vault.model.entity.SysConf;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SysConfRepo extends MongoRepository<SysConf,String>{

    
}

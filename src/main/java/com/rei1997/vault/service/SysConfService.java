package com.rei1997.vault.service;

import java.util.List;

import javax.annotation.PostConstruct;

import com.rei1997.vault.model.entity.SysConf;
import com.rei1997.vault.model.repository.SysConfRepo;
import com.rei1997.vault.util.SystemConfigUtil;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SysConfService {

    private final SysConfRepo sysConfRepo;

    // @EventListener(ApplicationReadyEvent.class)
    @PostConstruct
    public void reloadAllConfig(){
        List<SysConf> sysConf=sysConfRepo.findAll();
        for (SysConf sysConf2 : sysConf) {
            String key=sysConf2.getConfigKey();
            String value=sysConf2.getConfigValue();
            SystemConfigUtil.systemConfMap.put(key, value);
        }
    }
    
}

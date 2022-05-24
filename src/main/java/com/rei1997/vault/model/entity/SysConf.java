package com.rei1997.vault.model.entity;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data //lomobk
@Document //Collection
public class SysConf {
    
    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String configKey;
    private String configValue;
    private String comment;
    public SysConf(String configKey, String configValue, String comment) {
        this.configKey = configKey;
        this.configValue = configValue;
        this.comment = comment;
    }

    
}

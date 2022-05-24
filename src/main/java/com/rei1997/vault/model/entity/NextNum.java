package com.rei1997.vault.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class NextNum {
    @Id
    private String id;
    private long no;
}

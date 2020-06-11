package com.sync.syncjavbackend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class FingerPrint {
    @Id
    private String fingerPrint;
    private String[] userType;
    private Boolean block;
    private Boolean check;
    private FingerPrintUser[] user;
}

class FingerPrintUser{

    private String email;
    private String registerIP;
    private String userType;
}

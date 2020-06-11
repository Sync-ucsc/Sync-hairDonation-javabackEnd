package com.sync.syncjavbackend.models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Field("email")
    private String email;
    private String role;
    private Boolean temporaryBan;
    private String[] banAction;
    private String password;
    private Boolean active;

    public User(){
    }
}

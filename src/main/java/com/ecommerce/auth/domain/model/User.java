package com.ecommerce.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


// IDs are done with Long type
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private Integer age;
    private String phoneNumber;
}

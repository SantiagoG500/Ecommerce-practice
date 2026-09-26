package com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Ask why this didn't work  @GeneratedValue(strategy=GenerationType.UUID)
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;
    private Integer age;
    private String phoneNumber;
}

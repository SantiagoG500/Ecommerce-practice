package com.ecommerce.auth.infraestructure.mapper;

import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository.UserData;
import org.springframework.stereotype.Component;

@Component
public class MapperUser {

    public User toUser (UserData data) {
        if (data == null) {
            return null;
        }

        return new User(
            data.getId(),
            data.getUsername(),
            data.getEmail(),
            data.getPassword(),
            data.getAge(),
            data.getPhoneNumber()
        );
    }

    public UserData toData (User user) {
        if (user == null) {
            return null;
        }

        return new UserData(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getAge(),
            user.getPhoneNumber()
        );
    }

}

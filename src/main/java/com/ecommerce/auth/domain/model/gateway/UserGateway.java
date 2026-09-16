package com.ecommerce.auth.domain.model.gateway;


import com.ecommerce.auth.domain.model.User;

public interface UserGateway {

    User saveUser(User usr);

    User getUserById(String id);

    User updateUser(User user);

    void deleteUserById(String id);

}

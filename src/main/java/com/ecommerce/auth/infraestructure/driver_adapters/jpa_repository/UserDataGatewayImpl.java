package com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository;

import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.gateway.UserGateway;
import com.ecommerce.auth.infraestructure.mapper.MapperUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserDataGatewayImpl implements UserGateway {

    private final UserDataJpaRepository repository;
    private final MapperUser mapperUser;

    @Override
    public User saveUser(User user) {
        UserData userData = mapperUser.toData(user);
        // JPA will automatically generate the UUID here!
        UserData savedData = repository.save(userData);
        return mapperUser.toUser(savedData);
    }

//    public User saveUser(User user) {
//        UserData userData = mapperUser.toData(user);
//
//        if (repository.existsById( user.getId() )) {
//            UserData existingData = repository.findById( user.getId() )
//                    .orElseThrow();
//
//            return mapperUser.toUser( repository.saveAndFlush(existingData) );
//        }
//
//        return mapperUser.toUser( repository.save(userData) );
//    }


    @Override
    public User getUserById (String id) {
        return repository.findById(id)
           .map(mapperUser::toUser)
           .orElse(null);
    }

    @Override
    public User updateUser (User user) {
        UserData userData = mapperUser.toData(user);

        if (!repository.existsById(userData.getId())) {
            throw new RuntimeException(
                String.format("User with id %s not found", userData.getId())
            );
        }

        return mapperUser.toUser(repository.save(userData));
    }

    @Override
    public void deleteUserById (String id) {
       if (!repository.existsById(id)) {
           throw new RuntimeException(String.format("User with id %s not found", id));
       }

       repository.deleteById(id);
    }

}

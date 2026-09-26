package com.ecommerce.auth.domain.usecase;

import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase {

   private final UserGateway userGateway;

   public User saveUser(User user) {
       if (user == null) {
           throw new IllegalArgumentException("User object cannot be null");
       }

       if (user.getId() == null || user.getId().trim().isEmpty()) {
           user.setId(UUID.randomUUID().toString());
       }

       validateUserFields(user);
       return userGateway.saveUser(user);
   }

   public User getUserById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or be empty");
        }

        User foundUser = userGateway.getUserById(id);

       if (foundUser == null) {
           throw new IllegalArgumentException("User with id " + id + " not found");
       }

        return foundUser;
   }

   public User updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }

        if (user.getId() == null || user.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or be empty");
        }

        validateUserFields(user);
        return userGateway.updateUser(user);
   }

   public void deleteUserById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or be empty");
        }
        userGateway.deleteUserById(id);
   }

   public String login(String email, String pass){
       if (pass == null && email == null){
           throw new IllegalArgumentException("Credentials cannot be null");
       }

       User userToCheck = userGateway.getByEmail(email);

       if ( !Objects.equals(userToCheck.getEmail(), email) || !Objects.equals(userToCheck.getPassword(), pass) ){
           throw new IllegalArgumentException("Credentials do not match");
       }

       return "Authenticated as: " +  userToCheck.getEmail();
   }

    /**
     * Validates that an {@link User} object matches all required business rules.
     *
     * <p>The following fields are validated:
     * <ul>
     *   <li>The {@link User} object cannot be null</li>
     *   <li>The {@code id} field cannot be null or empty</li>
     *   <li>The {@code username} field cannot be null and be empty</li>
     *   <li>The {@code email} field should have a valid format</li>
     *   <li>The {@code password} field should have at least 6 characters</li>
     *   <li>The {@code age} should be at least 18 years old</li>
     * </ul>
     *
     * @param user The {@link User} object to validate
     * @throws IllegalArgumentException If user is null or if any field fails validation rules.
     */
    private void validateUserFields(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (user.getAge() == null || user.getAge() < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
    }
}

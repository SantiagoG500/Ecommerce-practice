package com.ecommerce.auth.infraestructure.entry_points;
import com.ecommerce.auth.domain.model.User;

import com.ecommerce.auth.domain.usecase.UserUseCase;
import com.ecommerce.auth.infraestructure.mapper.MapperUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("api/ecommerce/user")
@RequiredArgsConstructor

// TODO: Check what's a Request DTO
public class UserController {

    private final UserUseCase userUseCase;
    private final MapperUser mapperUser;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
       User savedUser = userUseCase.saveUser(user);
       return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userUseCase.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User savedUser = userUseCase.updateUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
       userUseCase.deleteUserById(id);

       return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}

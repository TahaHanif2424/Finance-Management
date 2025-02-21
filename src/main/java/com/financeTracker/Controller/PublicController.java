package com.financeTracker.Controller;

import com.financeTracker.Entity.UserEntity;
import com.financeTracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user") //localhost:8080/public/create-user
    public ResponseEntity<?> createuser(@RequestBody UserEntity user){
        userService.createNewUser(user);
        return new ResponseEntity<>("User Created successfully", HttpStatus.OK);
    }
}

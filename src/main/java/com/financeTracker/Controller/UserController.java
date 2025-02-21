package com.financeTracker.Controller;

import com.financeTracker.Entity.UserEntity;
import com.financeTracker.Repository.UserRepository;
import com.financeTracker.Service.UserService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username =authentication.getName();
        UserEntity userInDB=userRepository.findByUserName(username);
        if(userInDB!=null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.SaveUser(userInDB);
            return new ResponseEntity<>(userInDB,HttpStatus.OK);
        }
        return new ResponseEntity<>("Error while updating the user", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity user=userRepository.findByUserName(username);
        userRepository.deleteById(user.getId());
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }
    @PutMapping("/add-friend/{id}")
    public ResponseEntity<?> addFriend(@PathVariable ObjectId id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity user =userRepository.findByUserName(username);
        UserEntity friend =userRepository.findById(id).orElse(null);
        if(user.getId()==id){
            return new ResponseEntity<>("A user cannot add himself as friend",HttpStatus.BAD_REQUEST);
        }
        if(user==null || friend==null){
            return new ResponseEntity<>("Error!!!",HttpStatus.BAD_REQUEST);
        }
        if(user.getFriends()==null||!user.getFriends().contains(id)){
            user.getFriends().add(id);
            friend.getFriends().add(user.getId());
            userService.SaveUser(friend);
            userService.SaveUser(user);
            return new ResponseEntity<>("Friend Added Successfullt",HttpStatus.OK);
        }
        return new ResponseEntity<>("Friend already Exits",HttpStatus.BAD_REQUEST);
    }
}

package com.sync.syncjavbackend.controllers;

import com.sync.syncjavbackend.models.AuthenticationRequest;
import com.sync.syncjavbackend.models.AuthenticationResponse;
import com.sync.syncjavbackend.models.User;
import com.sync.syncjavbackend.repositories.UserRepository;
import com.sync.syncjavbackend.services.MyUserDetailsService;
import com.sync.syncjavbackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/users")
public class userController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get")
    public List<User> getAllProducts(){
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> createAuthenticationtoken(@RequestBody User user) throws Exception{
        try{
            String id = userRepository.findByEmail(user.getEmail()).getUserId();
            return ResponseEntity.badRequest().body("that email registered");
        } catch(Exception e){
            User newUser = new User();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());
            newUser.setBanAction(null);
            newUser.setTemporaryBan(false);
            newUser.setActive(true);
            newUser.setPassword(user.getPassword());
            userRepository.save(newUser);
            return ResponseEntity.ok(user);
        }


    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationtoken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }


    public User deleteUser(String email) {

        User deleteUser = userRepository.findByEmail(email);
        String id = deleteUser.getUserId();
        userRepository.deleteById(id);
        return deleteUser;
    }


    public User editUser(User user) {
        System.out.println(user);
        userRepository.save(user);
        return user;
    }

    public User register(User newUser){
        return newUser;
    }

    public ResponseEntity<?> active(){
        return ResponseEntity.ok("");
    }

    public ResponseEntity<?> banAction(){
        return ResponseEntity.ok("");
    }

    public ResponseEntity<?> temporaryBan(){
        return ResponseEntity.ok("");
    }

    public ResponseEntity<?> forgetPassword(){
        return ResponseEntity.ok("");
    }

    public ResponseEntity<?> changePassword(){
        return ResponseEntity.ok("");
    }


}

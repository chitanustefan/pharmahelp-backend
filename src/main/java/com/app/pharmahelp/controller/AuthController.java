package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.Drug;
import com.app.pharmahelp.entities.Pharmacist;
import com.app.pharmahelp.entities.User;
import com.app.pharmahelp.repository.UserRepo;
import com.app.pharmahelp.security.JwtTokenProvider;
import com.app.pharmahelp.security.payloads.ApiResponse;
import com.app.pharmahelp.security.payloads.JwtAuthenticationResponse;
import com.app.pharmahelp.services.DrugService;
import com.app.pharmahelp.services.PharmacistService;
import com.app.pharmahelp.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;

    @Autowired
    UserService userService = new UserService();

    @Autowired
    DrugService drugService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private PharmacistService pharmacistService=new PharmacistService();
    @Autowired
    private PharmacyStockController pharmacyStockController;

    @GetMapping("/nameandlocation")
    public Drug getDrugByNameAndLocation(@RequestParam String name,@RequestParam  String location){
        return drugService.findByNameAndDetails(name,location);
    }
    
    @GetMapping("/getbyid")
    public Drug getById(@RequestParam long id) {

        return drugService.findById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        Optional<User> user_logged;
        user_logged = userRepository.findByEmail(user.getEmail());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,user_logged.get().getRole()));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User registerRequest) {

        User user1 = new User();
        user1.setFullName(registerRequest.getFullName());
        user1.setEmail(registerRequest.getEmail());
        user1.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        if(userService.existsByEmail(registerRequest.getEmail())){

            user1.setRole("pharmacist");
            User user=userService.findByEmail(user1.getEmail()).get();
            userService.deleteUser(user.getId_user());
            userService.saveUser(user1);
            return ResponseEntity.ok(new ApiResponse(true, "Pharmacist registered successfully"));

        }else {
           user1.setRole("patient");
            userService.saveUser(user1);
            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
        }

    }

}

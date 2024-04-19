package org.thanhngo.retailapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thanhngo.retailapp.dtos.*;
import org.thanhngo.retailapp.dtos.UserLoginDTO;
import org.thanhngo.retailapp.services.UserService;
import org.thanhngo.retailapp.services.iUserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final iUserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO,
                                        BindingResult bindingResult ){
        try{
                if (bindingResult.hasErrors()) {
                    List<String> errorMessages = new ArrayList<>();
                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMessages.add(error.getDefaultMessage());
                    }
                    return ResponseEntity.badRequest().body(errorMessages);
                 }
            if(!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password and retype password must be the same");
            }
                userService.createUser(userDTO);
            return ResponseEntity.ok("User "+userDTO+" created successfully");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        // Check login and create token
        String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());
        // return token (in response)
        return ResponseEntity.ok("login scuccessfully with token: "+token);
    }
}

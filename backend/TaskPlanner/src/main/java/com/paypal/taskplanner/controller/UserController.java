package com.paypal.taskplanner.controller;

import com.paypal.taskplanner.dto.LoginDto;
import com.paypal.taskplanner.dto.UserDto;
import com.paypal.taskplanner.dto.UserResDto;
import com.paypal.taskplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResDto> registerUser(@RequestBody @Valid UserDto dto){
        return new ResponseEntity<>(service.registerUser(dto), HttpStatus.CREATED);
    }
    @PostMapping("/validate")
    public ResponseEntity<UserResDto> validateUser(@RequestBody @Valid LoginDto dto){
        return new ResponseEntity<>(service.validateUser(dto),HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<UserResDto> updateUser(@RequestBody @Valid UserDto dto){
        return new ResponseEntity<>(service.updateUser(dto),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<UserResDto> deleteUser(@Email @PathVariable String email){
        return new ResponseEntity<>(service.deleteUser(email),HttpStatus.OK);
    }
}

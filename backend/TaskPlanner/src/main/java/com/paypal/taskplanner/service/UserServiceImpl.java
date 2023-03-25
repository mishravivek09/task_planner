package com.paypal.taskplanner.service;

import com.paypal.taskplanner.dto.LoginDto;
import com.paypal.taskplanner.dto.UserDto;
import com.paypal.taskplanner.dto.UserResDto;
import com.paypal.taskplanner.entity.User;
import com.paypal.taskplanner.exception.MyRuntimeExceptions;
import com.paypal.taskplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResDto registerUser(UserDto dto) {
        User usr=userRepository.findByEmail(dto.getEmail());
        if(usr != null){
            throw new MyRuntimeExceptions("User is already registered with email : "+dto.getEmail());
        }
        User user=new User();
        user.setEmail(dto.getEmail());
        user.setCreatedDate(LocalDateTime.now());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setGender(dto.getGender());

        userRepository.save(user);

        UserResDto res=new UserResDto();
        res.setEmail(dto.getEmail());
        res.setCreatedDate(user.getCreatedDate());
        res.setGender(user.getGender());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setUserId(user.getUserId());

        return res;
    }

    @Override
    public UserResDto validateUser(LoginDto dto) {
        User user=userRepository.findByEmail(dto.getEmail());
        if(user==null){
            throw new MyRuntimeExceptions("Invalid email id : "+dto.getEmail());
        }
        if(! user.getPassword().equals(dto.getPassword())){
            throw new MyRuntimeExceptions("Invalid username or password");
        }
        UserResDto res=new UserResDto();
        res.setEmail(dto.getEmail());
        res.setCreatedDate(user.getCreatedDate());
        res.setGender(user.getGender());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setUserId(user.getUserId());
        return res;
    }

    @Override
    public UserResDto updateUser(UserDto dto) {
        User user=userRepository.findByEmail(dto.getEmail());
        if(user == null){
            throw new MyRuntimeExceptions("User is not registered with email : "+dto.getEmail());
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setGender(dto.getGender());

        userRepository.save(user);

        UserResDto res=new UserResDto();
        res.setEmail(dto.getEmail());
        res.setCreatedDate(user.getCreatedDate());
        res.setGender(user.getGender());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setUserId(user.getUserId());

        return res;
    }

    @Override
    public UserResDto deleteUser(String email) {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new MyRuntimeExceptions("User doesn't exist with email : "+email);
        }
        UserResDto res=new UserResDto();
        res.setEmail(user.getEmail());
        res.setCreatedDate(user.getCreatedDate());
        res.setGender(user.getGender());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setUserId(user.getUserId());
        userRepository.delete(user);
        return res;
    }
}

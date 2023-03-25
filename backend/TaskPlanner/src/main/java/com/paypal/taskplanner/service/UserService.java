package com.paypal.taskplanner.service;

import com.paypal.taskplanner.dto.LoginDto;
import com.paypal.taskplanner.dto.UserDto;
import com.paypal.taskplanner.dto.UserResDto;

public interface UserService {
    public UserResDto registerUser(UserDto dto);
    public UserResDto validateUser(LoginDto dto);
    public UserResDto updateUser(UserDto dto);
    public UserResDto deleteUser(String email);
}

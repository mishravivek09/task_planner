package com.paypal.taskplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @Email(message = "Please enter valid email")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message =
            "At least one upper case and one lower case English letter, At least one special characters and At least one digit ,should be minimum 8 in length ")
    private String password;
}

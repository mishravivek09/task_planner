package com.paypal.taskplanner.dto;

import com.paypal.taskplanner.myenum.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Size(min = 1,message = "Please enter first name")
    private String firstName;
    @Size(min = 1,message = "Please enter last name")
    private String lastName;
    @Email(message = "Please enter valid email")
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message =
            "At least one upper case and one lower case English letter, At least one special characters and At least one digit ,should be minimum 8 in length ")
    private String password;
    private Gender gender;
}

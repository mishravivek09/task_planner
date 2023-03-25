package com.paypal.taskplanner.dto;

import com.paypal.taskplanner.myenum.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private LocalDateTime createdDate;
}

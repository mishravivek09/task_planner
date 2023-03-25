package com.paypal.taskplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long userId;
    @Size(min = 1,message = "Please enter title")
    private String title;
    @Size(min = 3,message = "Please enter description")
    private String description;
}

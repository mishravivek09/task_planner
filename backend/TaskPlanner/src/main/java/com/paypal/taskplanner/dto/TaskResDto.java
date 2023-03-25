package com.paypal.taskplanner.dto;

import com.paypal.taskplanner.myenum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResDto {
    private long taskId;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private Status taskStatus;
}

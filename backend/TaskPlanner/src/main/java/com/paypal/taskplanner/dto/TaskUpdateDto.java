package com.paypal.taskplanner.dto;

import com.paypal.taskplanner.myenum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateDto {
    private Long taskId;
    private Status taskStatus;
}

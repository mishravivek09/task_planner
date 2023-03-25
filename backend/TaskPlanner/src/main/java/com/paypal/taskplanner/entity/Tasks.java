package com.paypal.taskplanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paypal.taskplanner.myenum.Status;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Task_Planner")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private Status taskStatus;
    @ManyToOne
    @JsonIgnore
    private User user;
}

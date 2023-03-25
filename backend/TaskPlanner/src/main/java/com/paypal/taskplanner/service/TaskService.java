package com.paypal.taskplanner.service;

import com.paypal.taskplanner.dto.TaskDto;
import com.paypal.taskplanner.dto.TaskResDto;
import com.paypal.taskplanner.dto.TaskUpdateDto;
import com.paypal.taskplanner.entity.Tasks;

import java.util.List;

public interface TaskService {
    public TaskResDto addTask(TaskDto dto);
    public TaskResDto deleteTask(Long taskId);
    public TaskResDto updateStatus(TaskUpdateDto dto);
    public List<Tasks> getAllTask(Long userId);
    public List<Tasks> getAllActiveTask(Long userId);
    public List<Tasks> getAllCompletedTask(Long userId);
}

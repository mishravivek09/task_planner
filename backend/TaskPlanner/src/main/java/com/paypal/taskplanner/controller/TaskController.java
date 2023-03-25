package com.paypal.taskplanner.controller;

import com.paypal.taskplanner.dto.TaskDto;
import com.paypal.taskplanner.dto.TaskResDto;
import com.paypal.taskplanner.dto.TaskUpdateDto;
import com.paypal.taskplanner.entity.Tasks;
import com.paypal.taskplanner.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    private TaskService service;
    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public ResponseEntity<TaskResDto> createNewTask(@RequestBody @Valid TaskDto dto){
        return new ResponseEntity<>(service.addTask(dto), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<TaskResDto> deleteTask(@RequestParam Long taskId){
        return new ResponseEntity<>(service.deleteTask(taskId),HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<TaskResDto> updateStatus(@Valid @RequestBody TaskUpdateDto dto){
        return new ResponseEntity<>(service.updateStatus(dto),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Tasks>> getAllTask(@RequestParam Long userId){
        return new ResponseEntity<>(service.getAllTask(userId),HttpStatus.OK);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<Tasks>> getAllActiveTask(@RequestParam Long userId){
        return new ResponseEntity<>(service.getAllActiveTask(userId),HttpStatus.OK);
    }
    @GetMapping("/completed")
    public ResponseEntity<List<Tasks>> getAllCompletedTask(@RequestParam Long userId){
        return new ResponseEntity<>(service.getAllCompletedTask(userId),HttpStatus.OK);
    }
}

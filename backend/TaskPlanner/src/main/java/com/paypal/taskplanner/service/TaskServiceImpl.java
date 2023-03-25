package com.paypal.taskplanner.service;

import com.paypal.taskplanner.dto.TaskDto;
import com.paypal.taskplanner.dto.TaskResDto;
import com.paypal.taskplanner.dto.TaskUpdateDto;
import com.paypal.taskplanner.entity.Tasks;
import com.paypal.taskplanner.entity.User;
import com.paypal.taskplanner.exception.MyRuntimeExceptions;
import com.paypal.taskplanner.myenum.Status;
import com.paypal.taskplanner.repository.TasksRepository;
import com.paypal.taskplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private TasksRepository tasksRepository;
    private UserRepository userRepository;
    @Autowired
    public TaskServiceImpl(TasksRepository tasksRepository, UserRepository userRepository) {
        this.tasksRepository = tasksRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskResDto addTask(TaskDto dto) {
        Optional<User> opt=userRepository.findById(dto.getUserId());
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("User doesn't exist with id : "+dto.getUserId());
        }
        User user=opt.get();
        Tasks task =new Tasks();
        task.setTaskStatus(Status.PENDING);
        task.setDateTime(LocalDateTime.now());
        task.setDescription(dto.getDescription());
        task.setTitle(dto.getTitle());
        task.setUser(user);
        user.getTasks().add(task);
        tasksRepository.save(task);

        TaskResDto res=new TaskResDto();
        res.setTaskId(task.getTaskId());
        res.setTaskStatus(task.getTaskStatus());
        res.setTitle(task.getTitle());
        res.setDescription(task.getDescription());
        res.setDateTime(task.getDateTime());

        return res;
    }

    @Override
    public TaskResDto deleteTask(Long taskId) {
        Optional<Tasks> opt=tasksRepository.findById(taskId);
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Invalid task id");
        }
        Tasks task=opt.get();

        TaskResDto res=new TaskResDto();
        res.setTaskId(task.getTaskId());
        res.setTaskStatus(task.getTaskStatus());
        res.setTitle(task.getTitle());
        res.setDescription(task.getDescription());
        res.setDateTime(task.getDateTime());

        tasksRepository.delete(task);

        return res;
    }

    @Override
    public TaskResDto updateStatus(TaskUpdateDto dto) {
        Optional<Tasks> opt=tasksRepository.findById(dto.getTaskId());
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Invalid task id");
        }
        Tasks task=opt.get();
        task.setTaskStatus(dto.getTaskStatus());
        tasksRepository.save(task);

        TaskResDto res=new TaskResDto();
        res.setTaskId(task.getTaskId());
        res.setTaskStatus(task.getTaskStatus());
        res.setTitle(task.getTitle());
        res.setDescription(task.getDescription());
        res.setDateTime(task.getDateTime());

        return res;
    }

    @Override
    public List<Tasks> getAllTask(Long userId) {
       Optional<User> opt=userRepository.findById(userId);
       if(!opt.isPresent()){
           throw new MyRuntimeExceptions("Invalid user id");
       }
       User user=opt.get();
        List<Tasks> list=user.getTasks();
        if(list.isEmpty()){
            throw new MyRuntimeExceptions("No tasks found..");
        }
       return list;

    }

    @Override
    public List<Tasks> getAllActiveTask(Long userId) {
        Optional<User> opt=userRepository.findById(userId);
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Invalid user id");
        }
        User user=opt.get();
        List<Tasks> list=tasksRepository.findAllByTaskStatus(Status.PENDING);
        List<Tasks> res=new ArrayList<>();
        list.forEach(t->{
            if(t.getUser().getUserId()==user.getUserId()){
                res.add(t);
            }
        });
        return res;
    }

    @Override
    public List<Tasks> getAllCompletedTask(Long userId) {
        Optional<User> opt=userRepository.findById(userId);
        if(!opt.isPresent()){
            throw new MyRuntimeExceptions("Invalid user id");
        }
        User user=opt.get();
        List<Tasks> list=tasksRepository.findAllByTaskStatus(Status.COMPLETED);
        List<Tasks> res=new ArrayList<>();
        list.forEach(t->{
            if(t.getUser().getUserId()==user.getUserId()){
                res.add(t);
            }
        });
        return res;
    }
}

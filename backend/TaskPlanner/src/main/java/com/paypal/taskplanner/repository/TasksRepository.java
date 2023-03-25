package com.paypal.taskplanner.repository;

import com.paypal.taskplanner.entity.Tasks;
import com.paypal.taskplanner.myenum.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks,Long> {
    public List<Tasks> findAllByTaskStatus(Status status);
}

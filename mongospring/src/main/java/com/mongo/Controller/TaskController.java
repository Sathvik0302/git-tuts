package com.mongo.Controller;

import com.mongo.Model.Task;
import com.mongo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task){
        try{
            Task addTask = taskService.addTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(addTask);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }

    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllTasks(){
        try{
            List<Task> allTask = taskService.findAllTask();
            return ResponseEntity.status(HttpStatus.OK).body(allTask);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error");
        }

    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<?> getById(@PathVariable String taskId){
        try{
            Task taskedById = taskService.taskById(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(taskedById);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error");
        }


    }

    @GetMapping("/getSeverity/{severity}")
    public ResponseEntity<?> getBySeverity(@PathVariable Integer severity){
    try{
        List<Task> taskBySeverity = taskService.getTaskBySeverity(severity);
        return ResponseEntity.status(HttpStatus.OK).body(taskBySeverity);

    }catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error");
    }

    }

    @GetMapping("/getAssignee/{assignee}")
    public ResponseEntity<?> getTaskByAssignee(@PathVariable String assignee){
        try{
            List<Task> taskByAssignee = taskService.getTaskByAssignee(assignee);
            return ResponseEntity.status(HttpStatus.OK).body(taskByAssignee);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody Task task){
        try{
            Task updateTask = taskService.updateTask(task);
            return ResponseEntity.status(HttpStatus.OK).body(updateTask);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error");
        }

    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String taskId){
        try{
            String Status = taskService.deleteTask(taskId);
            return ResponseEntity.status(HttpStatus.OK).body(Status);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server error");
        }

    }



}

package com.mongo.Service;

import com.mongo.Model.Task;
import com.mongo.Repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepo.save(task);
    }

    public List<Task> findAllTask(){
        List<Task> all = taskRepo.findAll();
        return all;
    }

    public Task taskById(String taskId) {
        Optional<Task> taskOptional = taskRepo.findById(taskId);
            return taskOptional.orElse(null);

    }

    public List<Task> getTaskBySeverity(int severity){
        return taskRepo.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee){
        return taskRepo.getTasksByAssignee(assignee);
    }

    public Task updateTask(Task task){
        Task existingTask = taskRepo.findById(task.getTaskId()).get();
        existingTask.setDescription(task.getDescription());
        existingTask.setAssignee(task.getAssignee());
        existingTask.setStoryPoint(task.getStoryPoint());
        existingTask.setSeverity(task.getSeverity());

      return  taskRepo.save(existingTask);
    }

    public String deleteTask(String taskId){
        try{
          if( taskRepo.existsById(taskId)){
              taskRepo.deleteById(taskId);
              return "Successfully deleted";
          }else{
              return "with the taskId nothing is found";
          }
        }catch (Exception e){
            return"failed to delete book";
        }

    }


}

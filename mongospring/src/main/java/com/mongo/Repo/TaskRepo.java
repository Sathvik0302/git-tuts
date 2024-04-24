package com.mongo.Repo;

import com.mongo.Model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface TaskRepo extends MongoRepository<Task, Serializable> {
    List<Task> findBySeverity(int severity);

    @Query("{assignee: ?0}")
    List<Task> getTasksByAssignee(String assignee);

}

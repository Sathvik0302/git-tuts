package com.project.ems.Repository;

import com.project.ems.Entity.Employee;
import com.project.ems.Entity.MasterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface EMSRepo extends JpaRepository<Employee, Serializable> {

}

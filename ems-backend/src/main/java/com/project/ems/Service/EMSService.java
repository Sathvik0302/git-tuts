package com.project.ems.Service;

import com.project.ems.DTO.EmployeeDto;
import com.project.ems.Entity.MasterDto;
import org.springframework.stereotype.Service;

@Service
public interface EMSService {



    MasterDto getByEmployeeById(long id);

    MasterDto getEmployeesData();

    MasterDto upsert(EmployeeDto employeeDto);

    MasterDto deleteById(long id);
}

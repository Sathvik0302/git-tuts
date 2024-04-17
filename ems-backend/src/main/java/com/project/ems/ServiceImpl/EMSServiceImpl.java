package com.project.ems.ServiceImpl;

import com.project.ems.DTO.EmployeeDto;
import com.project.ems.Entity.Employee;
import com.project.ems.Entity.MasterDto;
import com.project.ems.Mapper.EmployeeMapper;
import com.project.ems.Repository.EMSRepo;
import com.project.ems.Service.EMSService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EMSServiceImpl implements EMSService {

    @Autowired
    public EMSRepo emsRepo;


    @Override
    public MasterDto getByEmployeeById(long id) {
        MasterDto masterDto = new MasterDto();
        try{
            Optional<Employee> employeeById = emsRepo.findById(id);
            if(employeeById.isPresent()){
                EmployeeDto employeeDto = new EmployeeDto();
                Employee employee = employeeById.get();
                employeeDto.setId(employee.getId());
                employeeDto.setFirstName(employee.getFirstName());
                employeeDto.setLastName(employee.getLastName());
                employeeDto.setEmail(employee.getEmail());

                masterDto.setStatus(true);
                masterDto.setMessage("Employee details by id successful ");
                masterDto.setData(employeeDto);

            }else{
                masterDto.setStatus(false);
                masterDto.setMessage("Employee by required Id is not present");
                masterDto.setData(new ArrayList<>());
            }

        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred :" + e.getMessage());
            masterDto.setData(new ArrayList<>());
        }
        return masterDto;
    }

    @Override
    public MasterDto getEmployeesData() {
        MasterDto masterDto = new MasterDto();
        try {
            List<Employee> allEmployees = emsRepo.findAll();

            List<EmployeeDto> employeeDtos = new ArrayList<>();

            for(Employee eachEmployee :allEmployees ){
                EmployeeDto employeeDto = new EmployeeDto();

                employeeDto.setId(eachEmployee.getId());
                employeeDto.setFirstName(eachEmployee.getFirstName());
                employeeDto.setLastName(eachEmployee.getLastName());
                employeeDto.setEmail(eachEmployee.getEmail());

                employeeDtos.add(employeeDto);
            }
            masterDto.setStatus(true);
            masterDto.setMessage("Data Got Successfully");
            masterDto.setData(employeeDtos);

        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred :" + e.getMessage());
            masterDto.setData(new ArrayList<>());
        }
        return masterDto;
    }

    @Override
    public MasterDto upsert(EmployeeDto employeeDto) {
        MasterDto masterDto = new MasterDto();
        try {

            Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

            Optional<Employee> existingEmployeeOptional = emsRepo.findById(employeeDto.getId());

            if (existingEmployeeOptional.isPresent()) {
                Employee existingEmployee = existingEmployeeOptional.get();

                existingEmployee.setFirstName(employee.getFirstName());
                existingEmployee.setLastName(employee.getLastName());
                existingEmployee.setEmail(employee.getEmail());

                emsRepo.save(existingEmployee);

                employeeDto = EmployeeMapper.mapToEmployeeDto(existingEmployee);
            } else {
                emsRepo.save(employee);
                employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
            }


            masterDto.setStatus(true);
            masterDto.setMessage("Employee updated successfully");
            masterDto.setData(Collections.singletonList(employeeDto));
        } catch (DataAccessException e) {

            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred: " + e.getMessage());
            masterDto.setData(new ArrayList<>());
        }
        return masterDto;
    }




    @Override
    public MasterDto deleteById(long id) {
        MasterDto masterDto = new MasterDto();
        try{
            boolean b = emsRepo.existsById(id);
            if(b){
                emsRepo.deleteById(id);

                masterDto.setStatus(true);
                masterDto.setMessage("Employee details by id is deleted successfully ");
                masterDto.setData(null);

            }else{
                masterDto.setStatus(false);
                masterDto.setMessage("Employee by required Id is not present");
                masterDto.setData(new ArrayList<>());
            }

        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred :" + e.getMessage());
            masterDto.setData(new ArrayList<>());
        }
        return masterDto;
    }
}

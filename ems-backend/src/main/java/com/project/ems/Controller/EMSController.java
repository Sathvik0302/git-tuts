package com.project.ems.Controller;

import com.project.ems.DTO.EmployeeDto;
import com.project.ems.Entity.MasterDto;
import com.project.ems.Service.EMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ems")
public class EMSController {

    @Autowired
    private EMSService emsService;

    @GetMapping("/employees")
    public ResponseEntity<MasterDto> getAllEmployees(){
        MasterDto masterDto = new MasterDto();
        try{
            masterDto = emsService.getEmployeesData();
            return ResponseEntity.of(Optional.of(masterDto));
        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred  :" +e.getMessage());
            masterDto.setData(null);
            return ResponseEntity.of(Optional.of(masterDto));
        }
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<MasterDto> getEmployeesById(@PathVariable long id){
        MasterDto masterDto = new MasterDto();
        try{
            masterDto=emsService.getByEmployeeById(id);
            return ResponseEntity.of(Optional.of(masterDto));

        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred :" + e.getMessage());
            masterDto.setData(null);
            return ResponseEntity.of(Optional.of(masterDto));
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<MasterDto> upsert(@RequestBody EmployeeDto employeeDto){
        MasterDto masterDto = new MasterDto();
        try{
            masterDto=emsService.upsert(employeeDto);
            return ResponseEntity.of(Optional.of(masterDto));

        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred :" + e.getMessage());
            masterDto.setData(null);
           return ResponseEntity.of(Optional.of(masterDto));
        }
    }

    @PutMapping("/employees")
    public ResponseEntity<MasterDto> updating(@RequestBody EmployeeDto employeeDto) {
        MasterDto masterDto = new MasterDto();
        try{
            masterDto=emsService.upsert(employeeDto);
            return ResponseEntity.of(Optional.of(masterDto));

        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred :" + e.getMessage());
            masterDto.setData(null);
            return ResponseEntity.of(Optional.of(masterDto));
        }
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<MasterDto> deleteByID(@PathVariable long id){
        MasterDto masterDto = new MasterDto();
        try{
            masterDto = emsService.deleteById(id);
            return  ResponseEntity.of(Optional.of(masterDto));

        }catch(Exception e){
            e.printStackTrace();
            masterDto.setStatus(false);
            masterDto.setMessage("Exception occurred :" + e.getMessage());
            masterDto.setData(null);
            return ResponseEntity.of(Optional.of(masterDto));
        }
    }

}

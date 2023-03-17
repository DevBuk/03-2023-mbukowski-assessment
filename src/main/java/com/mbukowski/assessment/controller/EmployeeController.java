package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.DTO.AverageSalaryByPositionAndSeniorityOfEmployeesDTO;
import com.mbukowski.assessment.DTO.EmployeeDTO;
import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/average")
    List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> averageSalariesByPositionsAndSeniorityForAllEmployeesInDB(){
        return employeeService.averageSalariesByPositionsAndSeniorityForAllEmployeesInDB();
    }

    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeEntityById(@PathVariable Long id) {
        return employeeService.getEmployeeEntityById(id);
    }

    @GetMapping("/{name_of_employee}/{surname_of_employee}")
    public EmployeeEntity getEmployeeEntityByNameAndSurname(
            @PathVariable("name_of_employee") String nameOfEmployee,
            @PathVariable("surname_of_employee") String surnameOfEmployee
    ) {
        return employeeService.getEmployeeEntityByNameAndSurname(nameOfEmployee,surnameOfEmployee);
    }

    @PostMapping("/addEmployee")
    public EmployeeEntity addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployeeEntityToDB(employeeDTO);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTOWithNewData) {
        return employeeService.updateEmployee(id,employeeDTOWithNewData);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

}

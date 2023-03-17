package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.DTO.AverageSalaryByPositionAndSeniorityOfEmployeesDTO;
import com.mbukowski.assessment.DTO.EmployeeDTO;
import com.mbukowski.assessment.entity.AddressEntity;
import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.repository.AddressRepository;
import com.mbukowski.assessment.repository.DepartmentRepository;
import com.mbukowski.assessment.repository.EmployeeRepository;
import com.mbukowski.assessment.repository.PositionRepository;
import com.mbukowski.assessment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/average")
    List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> averageSalariesByPositionsAndSeniorityForAllEmployeesInDB(){
        List<EmployeeEntity> allEmployeesFromDB = employeeRepository.findAll();
        return employeeService.getAverageSalariesByPositionsAndSeniorityForAllEmployeesInDB(allEmployeesFromDB);
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
        return employeeRepository.findEmployeeEntityByNameAndSurname(nameOfEmployee, surnameOfEmployee);
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
        EmployeeEntity employeeEntityToDelete = employeeService.getEmployeeEntityById(id);
        AddressEntity addressEntityOfEmployeeToDelete = employeeEntityToDelete.getAddressEntity();
        employeeRepository.delete(employeeEntityToDelete);
        addressRepository.delete(addressEntityOfEmployeeToDelete);
    }

}

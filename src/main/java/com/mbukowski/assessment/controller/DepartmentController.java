package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.DTO.DepartmentDTO;
import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.repository.AddressRepository;
import com.mbukowski.assessment.repository.DepartmentRepository;
import com.mbukowski.assessment.repository.EmployeeRepository;
import com.mbukowski.assessment.service.AddressService;
import com.mbukowski.assessment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    AddressService addressService;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public DepartmentEntity getDepartmentEntityById(@PathVariable Long id){
        return departmentService.getDepartmentEntityById(id);
    }

    @GetMapping("/{department_name}/{city_of_department}")
    public DepartmentEntity getDepartmentEntityByNameAndCity(
            @PathVariable("department_name") String departmentName,
            @PathVariable("city_of_department") String cityOfDepartment
    ) {
        return departmentRepository.findDepartmentEntityByDepartmentNameAndAddressEntity_City(departmentName,cityOfDepartment);
    }

    @PostMapping("/addDepartment")
    public DepartmentEntity addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = departmentService.convertDepartmentDTOToDepartmentEntity(departmentDTO);
        addressRepository.save(departmentEntity.getAddressEntity());
        return departmentRepository.save(departmentEntity);
    }

    @PutMapping("/{id}")
    public DepartmentEntity updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(id,departmentDTO);
    }

    @DeleteMapping("/{id}")
    void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartmentEntityById(id);
        }

}






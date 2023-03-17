package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.DTO.DepartmentDTO;
import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/{id}")
    public DepartmentEntity getDepartmentEntityById(@PathVariable Long id){
        return departmentService.getDepartmentEntityById(id);
    }

    @GetMapping("/{department_name}/{city_of_department}")
    public DepartmentEntity getDepartmentEntityByNameAndCity(
            @PathVariable("department_name") String departmentName,
            @PathVariable("city_of_department") String cityOfDepartment
    ) {
        return departmentService.getDepartmentEntityByNameAndCity(departmentName, cityOfDepartment);
    }

    @PostMapping("/addDepartment")
    public DepartmentEntity addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addDepartmentEntityToDB(departmentDTO);
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






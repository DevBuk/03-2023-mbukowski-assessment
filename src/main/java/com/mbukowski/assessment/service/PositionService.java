package com.mbukowski.assessment.service;

import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    EmployeeRepository employeeRepository;

    /*
    I am aware that set fields on NULL is not the best practice,
    but on time which I have that solution must be sufficient.
    */
    public void forDeletingPositionClearPositionEntityFieldsForEmployees(List<EmployeeEntity> employeeEntityList){
        employeeEntityList.forEach(x->x.setPositionEntity(null));
        for(EmployeeEntity employeeEntity : employeeEntityList){
            employeeRepository.save(employeeEntity);
        }
    }

}

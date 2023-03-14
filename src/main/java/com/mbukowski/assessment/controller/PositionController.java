package com.mbukowski.assessment.controller;

import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.entity.PositionEntity;
import com.mbukowski.assessment.repository.EmployeeRepository;
import com.mbukowski.assessment.repository.PositionRepository;
import com.mbukowski.assessment.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionRepository positionRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PositionService positionService;

    @GetMapping("/{id}")
    public PositionEntity getPositionById(@PathVariable Long id){
        return positionRepository.findById(id).<NoSuchElementException>orElseThrow(() -> {
            throw new NoSuchElementException("Not found position with id: " + id);
        });
    }

    @PostMapping("/addPosition")
    public PositionEntity addPosition(@RequestBody PositionEntity positionEntity) {
        return positionRepository.save(positionEntity);
    }

    @PutMapping("/{id}")
    public PositionEntity updatePosition(@PathVariable Long id, @RequestBody PositionEntity positionWithNewDataToUpdate) {

        PositionEntity positionEntityToUpdate = positionRepository.findById(id).<NoSuchElementException>orElseThrow(()-> {
            throw new NoSuchElementException("Not found position with id" + id);
        });
        positionEntityToUpdate.setJobName(positionWithNewDataToUpdate.getJobName());
        return positionRepository.save(positionEntityToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        PositionEntity positionEntity = positionRepository.findById(id).<NoSuchElementException>orElseThrow(() -> {
            throw new NoSuchElementException("Not found position with id: " + id);
        });
        List<EmployeeEntity> employeeEntityList = employeeRepository.findEmployeeEntitiesByPositionEntity(positionEntity);
        positionService.forDeletingPositionClearPositionEntityFieldsForEmployees(employeeEntityList);
        positionRepository.delete(positionEntity);
    }

}

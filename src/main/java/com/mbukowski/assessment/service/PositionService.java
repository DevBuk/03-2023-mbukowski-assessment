package com.mbukowski.assessment.service;

import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.entity.PositionEntity;
import com.mbukowski.assessment.repository.EmployeeRepository;
import com.mbukowski.assessment.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PositionService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    /*
    I am aware that set fields on NULL is not the best practice,
    but on time which I have that solution must be sufficient.
    */
    private void forDeletingPositionClearPositionEntityFieldsForEmployees(List<EmployeeEntity> employeeEntityList){
        employeeEntityList.forEach(x->x.setPositionEntity(null));
        for(EmployeeEntity employeeEntity : employeeEntityList){
            employeeRepository.save(employeeEntity);
        }
    }

    public PositionEntity getPositionEntityById(Long id){
        return positionRepository.findById(id).<NoSuchElementException>orElseThrow(() -> {
            throw new NoSuchElementException("Not found position with id: " + id);
        });
    }

    public PositionEntity updatePosition(Long id,PositionEntity positionWithNewDataToUpdate) {
        PositionEntity positionEntityToUpdate = positionRepository.findById(id).<NoSuchElementException>orElseThrow(()-> {
            throw new NoSuchElementException("Not found position with id" + id);
        });
        positionEntityToUpdate.setJobName(positionWithNewDataToUpdate.getJobName());
        return positionRepository.save(positionEntityToUpdate);
    }

    public void deletePosition(Long id) {
        PositionEntity positionEntity = positionRepository.findById(id).<NoSuchElementException>orElseThrow(() -> {
            throw new NoSuchElementException("Not found position with id: " + id);
        });
        List<EmployeeEntity> employeeEntityList = employeeRepository.findEmployeeEntitiesByPositionEntity(positionEntity);
        forDeletingPositionClearPositionEntityFieldsForEmployees(employeeEntityList);
        positionRepository.delete(positionEntity);
    }

    public PositionEntity addPosition(PositionEntity positionEntity) {
        return positionRepository.save(positionEntity);
    }

}

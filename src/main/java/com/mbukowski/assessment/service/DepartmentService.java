package com.mbukowski.assessment.service;

import com.mbukowski.assessment.DTO.DepartmentDTO;
import com.mbukowski.assessment.entity.AddressEntity;
import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.repository.AddressRepository;
import com.mbukowski.assessment.repository.DepartmentRepository;
import com.mbukowski.assessment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    @Autowired
    AddressService addressService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AddressRepository addressRepository;

    private DepartmentEntity convertDepartmentDTOToDepartmentEntity(DepartmentDTO departmentDTO){
        return new DepartmentEntity(
                departmentDTO.getDepartmentName(),
                new AddressEntity(
                        departmentDTO.getStreetOfDepartment(),
                        departmentDTO.getHouseNumberOfDepartment(),
                        departmentDTO.getPostcodeOfDepartment(),
                        departmentDTO.getCityOfDepartment())
        );
    }

    public DepartmentEntity updateDepartment (Long idDepartmentEntityToUpdate, DepartmentDTO departmentDTOWithNewDataToUpdate){

        DepartmentEntity departmentEntityToUpdate = departmentRepository.findById(idDepartmentEntityToUpdate).<NoSuchElementException>orElseThrow(()-> {
            throw new NoSuchElementException("Not found department with id" + idDepartmentEntityToUpdate);
        });
        addressService.updateAddressOfDepartment(departmentEntityToUpdate,departmentDTOWithNewDataToUpdate);
        departmentEntityToUpdate.setDepartmentName(departmentDTOWithNewDataToUpdate.getDepartmentName());
        return departmentRepository.save(departmentEntityToUpdate);
    }

    public DepartmentEntity getDepartmentEntityById(Long id){
        return departmentRepository.findById(id).<NoSuchElementException>orElseThrow(() -> {
            throw new NoSuchElementException("Not found department with id: " + id);
        });
    }

    public void deleteDepartmentEntityById(Long id){
        DepartmentEntity departmentEntity = getDepartmentEntityById(id);
        List<EmployeeEntity> employeeEntityList = employeeRepository.findEmployeeEntitiesByDepartmentEntity(departmentEntity);
        forDeletingDepartmentClearDepartmentEntityFieldsForEmployees(employeeEntityList);
        departmentRepository.delete(departmentEntity);
        forDeletingDepartmentDeleteHisAddress(departmentEntity);
    }

    /*
    I am aware that set fields on NULL is not the best practice,
    but on time which I have that solution must be sufficient.
    */
    private void forDeletingDepartmentClearDepartmentEntityFieldsForEmployees(List<EmployeeEntity> employeeEntityList){
        employeeEntityList.forEach(x->x.setDepartmentEntity(null));
        for(EmployeeEntity employeeEntity : employeeEntityList){
            employeeRepository.save(employeeEntity);
        }
    }

    private void forDeletingDepartmentDeleteHisAddress(DepartmentEntity departmentEntity){
        AddressEntity addressEntity = departmentEntity.getAddressEntity();
        addressRepository.delete(addressEntity);
    }

    public DepartmentEntity addDepartmentEntityToDB(DepartmentDTO departmentDTO){
        DepartmentEntity departmentEntity = convertDepartmentDTOToDepartmentEntity(departmentDTO);
        addressRepository.save(departmentEntity.getAddressEntity());
        return departmentRepository.save(departmentEntity);
    }



}

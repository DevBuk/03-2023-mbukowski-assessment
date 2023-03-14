package com.mbukowski.assessment.service;

import com.mbukowski.assessment.DTO.DepartmentDTO;
import com.mbukowski.assessment.entity.AddressEntity;
import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressEntity updateAddressOfDepartment (DepartmentEntity departmentEntityForUpdateAddress, DepartmentDTO departmentDTOWithAddressToUpdate){
        AddressEntity addressEntityToBeUpdated = departmentEntityForUpdateAddress.getAddressEntity();
        addressEntityToBeUpdated.setStreet(departmentDTOWithAddressToUpdate.getStreetOfDepartment());
        addressEntityToBeUpdated.setHouseNumber(departmentDTOWithAddressToUpdate.getHouseNumberOfDepartment());
        addressEntityToBeUpdated.setPostcode(departmentDTOWithAddressToUpdate.getPostcodeOfDepartment());
        addressEntityToBeUpdated.setCity(departmentDTOWithAddressToUpdate.getCityOfDepartment());
        return addressRepository.save(addressEntityToBeUpdated);
    }

}

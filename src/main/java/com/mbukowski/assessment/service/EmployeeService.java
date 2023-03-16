package com.mbukowski.assessment.service;

import com.mbukowski.assessment.DTO.AverageSalaryByPositionAndSeniorityOfEmployeesDTO;
import com.mbukowski.assessment.DTO.EmployeeDTO;
import com.mbukowski.assessment.entity.AddressEntity;
import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.entity.PositionEntity;
import com.mbukowski.assessment.repository.AddressRepository;
import com.mbukowski.assessment.repository.DepartmentRepository;
import com.mbukowski.assessment.repository.EmployeeRepository;
import com.mbukowski.assessment.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    PositionRepository positionRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDTO convertEntityToDto(EmployeeEntity employeeEntity){
        return new EmployeeDTO(
                employeeEntity.getName(),
                employeeEntity.getSurname(),
                employeeEntity.getEmail(),
                employeeEntity.getPhoneNumber(),
                employeeEntity.getDateOfEmployment(),
                employeeEntity.getSalary(),

                employeeEntity.getAddressEntity().getStreet(),
                employeeEntity.getAddressEntity().getHouseNumber(),
                employeeEntity.getAddressEntity().getPostcode(),
                employeeEntity.getAddressEntity().getCity(),

                employeeEntity.getDepartmentEntity().getDepartmentName(),
                employeeEntity.getDepartmentEntity().getAddressEntity().getStreet(),
                employeeEntity.getDepartmentEntity().getAddressEntity().getHouseNumber(),
                employeeEntity.getDepartmentEntity().getAddressEntity().getPostcode(),
                employeeEntity.getDepartmentEntity().getAddressEntity().getCity(),

                employeeEntity.getPositionEntity().getJobName()
        );
    }

    public EmployeeEntity getEmployeeEntityById(Long id){
        return employeeRepository.findById(id).<NoSuchElementException>orElseThrow(()-> {
            throw new NoSuchElementException("Not found employee with id" + id);
        });
    }

    private AddressEntity convertsEmployeeDTOToAddressEntityForEmployee(EmployeeDTO employeeDTO){
        return new AddressEntity(
                employeeDTO.getStreetOfEmployee(),
                employeeDTO.getHouseNumberOfEmployee(),
                employeeDTO.getPostcodeOfEmployee(),
                employeeDTO.getCityOfEmployee()
        );
    }

    public EmployeeEntity convertEmployeeDTOToEmployeeEntity(EmployeeDTO employeeDTO){

        DepartmentEntity departmentEntity = checksIfDepartmentOfAddedEmployeeExistsInDBIfNotAddsItToDB(employeeDTO);
        PositionEntity positionEntity = checksIfPositionOfAddedEmployeeExistsInDBIfNotAddsItToDB(employeeDTO);
        AddressEntity addressOfEmployee = convertsEmployeeDTOToAddressEntityForEmployee(employeeDTO);

        return new EmployeeEntity(
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getEmail(),
                employeeDTO.getPhoneNumber(),
                employeeDTO.getDateOfEmployment(),
                employeeDTO.getSalary(),
                addressOfEmployee,
                departmentEntity,
                positionEntity
        );
    }

    private DepartmentEntity checksIfDepartmentOfAddedEmployeeExistsInDBIfNotAddsItToDB(EmployeeDTO employeeDTO){
        DepartmentEntity departmentEntity = null;
        List<DepartmentEntity> listOfAllDepartments = departmentRepository.findAll();
        for(DepartmentEntity unit : listOfAllDepartments){
            if(unit.getDepartmentName().equals(employeeDTO.getDepartmentName()) && unit.getAddressEntity().getCity().equals(employeeDTO.getCityOfDepartment())){
                departmentEntity = unit;
                break;
            }
        }
        if(departmentEntity==null){
            AddressEntity addressOfDepartment = new AddressEntity(
                    employeeDTO.getStreetOfDepartment(),
                    employeeDTO.getHouseNumberOfDepartment(),
                    employeeDTO.getPostcodeOfDepartment(),
                    employeeDTO.getCityOfDepartment()
            );
            departmentEntity =new DepartmentEntity(
                    employeeDTO.getDepartmentName(),
                    addressOfDepartment
            );
            addressRepository.save(addressOfDepartment);
            departmentRepository.save(departmentEntity);
        }
        return departmentEntity;
    }

    private PositionEntity checksIfPositionOfAddedEmployeeExistsInDBIfNotAddsItToDB(EmployeeDTO employeeDTO){
        PositionEntity positionEntity = null;
        List<PositionEntity> listOfAllPositions = positionRepository.findAll();
        for(PositionEntity unit : listOfAllPositions){
            if(unit.getJobName().equals(employeeDTO.getJobName())){
                positionEntity = unit;
                break;
            }
        }
        if(positionEntity==null){
            positionEntity = new PositionEntity(employeeDTO.getJobName());
            positionRepository.save(positionEntity);
        }
        return positionEntity;
    }

    public EmployeeEntity updateEmployee (Long id, EmployeeDTO employeeDTOWithNewData){
        Long IdAddressEntityWhichIsRequiredToUpdatedNewData = getEmployeeEntityById(id).getAddressEntity().getId();
        EmployeeEntity employeeEntityWithUpdatedData = convertEmployeeDTOToEmployeeEntity(employeeDTOWithNewData);
        employeeEntityWithUpdatedData.setId(id);
        employeeEntityWithUpdatedData.getAddressEntity().setId(IdAddressEntityWhichIsRequiredToUpdatedNewData);
        addressRepository.save(employeeEntityWithUpdatedData.getAddressEntity());
        return employeeRepository.save(employeeEntityWithUpdatedData);
    }

    private Integer getDifferenceBetweenDateOfEmploymentAndNowInYears(LocalDate dateOfEmployment){
        Period period = Period.between(dateOfEmployment, LocalDate.now());
        double sum = period.getYears()*365 + period.getMonths()*30 + period.getDays();
        return (int)Math.round(sum/365);
    }

    private List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>
    convertListOfEmployeeEntitiesOnListOfAverageSalaryByPositionAndSeniorityOfEmployeesDTO(List<EmployeeEntity> listOftEmployeeEntities){
        List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> listOfEmployeesFromDBConvertedToTargetDTO = new ArrayList<>();

        for(EmployeeEntity employeeEntityUnit : listOftEmployeeEntities){
            listOfEmployeesFromDBConvertedToTargetDTO.add(new AverageSalaryByPositionAndSeniorityOfEmployeesDTO(
                    employeeEntityUnit.getPositionEntity().getJobName(),
                    getDifferenceBetweenDateOfEmploymentAndNowInYears(employeeEntityUnit.getDateOfEmployment()),
                    employeeEntityUnit.getSalary()
            ));
        }
        return listOfEmployeesFromDBConvertedToTargetDTO;
    }

    private Map<String, List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>>
    groupListOfAverageSalaryByPositionAndSeniorityOfEmployeesDTOByJobName(List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> list){
        return list
                .stream()
                .collect(Collectors.groupingBy(AverageSalaryByPositionAndSeniorityOfEmployeesDTO::getJobName));
    }

    /*
    the method requires breaking down into smaller pieces
    */
    private List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>
    getGroupedListsOfAverageSalaryByPositionAndSeniorityOfEmployeesDTOByJobNameAndReturnListOfAverageSalariesByPositionAndSeniorityOfEmployeesDTO(Map<String, List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>> groupedByJobName){
        List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> listOfResults = new ArrayList<>();

        for (Map.Entry<String, List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>> entryByJobName : groupedByJobName.entrySet()) {
            List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> listForOneJobName = entryByJobName.getValue();
            Map<Integer, List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>> groupBySeniorityForSpecifiedOneJobName = listForOneJobName
                    .stream()
                    .collect(Collectors.groupingBy(AverageSalaryByPositionAndSeniorityOfEmployeesDTO::getSeniority));
            for(Map.Entry<Integer, List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>> entryBySeniority :groupBySeniorityForSpecifiedOneJobName.entrySet()){
                List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> listGroupedByJobNameAndSeniority = entryBySeniority.getValue();
                BigDecimal sumOfAllSalariesForOneSpecifiedJobNameAndSpecifiedSeniority = listGroupedByJobNameAndSeniority
                        .stream()
                        .map(AverageSalaryByPositionAndSeniorityOfEmployeesDTO::getAverageSalary)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                long numberOfAllSalariesForOneSpecifiedJobNameAndSpecifiedSeniority = listGroupedByJobNameAndSeniority.stream().count();
                BigDecimal averageOfSalariesForOneSpecifiedJobNameAndSpecifiedSeniority = sumOfAllSalariesForOneSpecifiedJobNameAndSpecifiedSeniority
                        .divide(new BigDecimal(numberOfAllSalariesForOneSpecifiedJobNameAndSpecifiedSeniority), 2);
                listGroupedByJobNameAndSeniority.get(0).setAverageSalary(averageOfSalariesForOneSpecifiedJobNameAndSpecifiedSeniority);
                listOfResults.add(listGroupedByJobNameAndSeniority.get(0));
            }
        }
        return listOfResults;
    }

    public List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> getAverageSalariesByPositionsAndSeniorityForAllEmployeesInDB(List<EmployeeEntity> list){
        List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO> listOfAllEmployeesFromDBConvertedToTargetDTO =
                convertListOfEmployeeEntitiesOnListOfAverageSalaryByPositionAndSeniorityOfEmployeesDTO(list);
        Map<String, List<AverageSalaryByPositionAndSeniorityOfEmployeesDTO>> groupByJobName =
                groupListOfAverageSalaryByPositionAndSeniorityOfEmployeesDTOByJobName(listOfAllEmployeesFromDBConvertedToTargetDTO);
        return getGroupedListsOfAverageSalaryByPositionAndSeniorityOfEmployeesDTOByJobNameAndReturnListOfAverageSalariesByPositionAndSeniorityOfEmployeesDTO(groupByJobName);
    }

    public EmployeeEntity saveEmployeeEntityInDB(EmployeeEntity employeeEntity){
        addressRepository.save(employeeEntity.getAddressEntity());
        return employeeRepository.save(employeeEntity);
    }

}

package com.mbukowski.assessment.repository;

import com.mbukowski.assessment.entity.DepartmentEntity;
import com.mbukowski.assessment.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findEmployeeEntityByNameAndSurname(String name, String surname);

    List<EmployeeEntity> findEmployeeEntitiesByDepartmentEntity(DepartmentEntity departmentEntity);

}

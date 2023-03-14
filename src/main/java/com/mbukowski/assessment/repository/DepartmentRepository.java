package com.mbukowski.assessment.repository;

import com.mbukowski.assessment.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    DepartmentEntity findDepartmentEntityByDepartmentNameAndAddressEntity_City(String departmentName, String cityOfDepartment);

}

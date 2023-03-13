package com.mbukowski.assessment.repository;

import com.mbukowski.assessment.entity.EmployeeEntity;
import com.mbukowski.assessment.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
}

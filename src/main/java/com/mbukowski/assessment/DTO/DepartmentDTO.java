package com.mbukowski.assessment.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class DepartmentDTO {
    private String departmentName;
    private String streetOfDepartment;
    private String houseNumberOfDepartment;
    private String postcodeOfDepartment;
    private String cityOfDepartment;
}

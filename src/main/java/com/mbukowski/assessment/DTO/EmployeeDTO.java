package com.mbukowski.assessment.DTO;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class EmployeeDTO {
    private String name;
    private String surname;
    private String email;
    private Integer phoneNumber;
    private LocalDate dateOfEmployment;
    private BigDecimal salary;
    private String streetOfEmployee;
    private String houseNumberOfEmployee;
    private String postcodeOfEmployee;
    private String cityOfEmployee;
    private String departmentName;
    private String streetOfDepartment;
    private String houseNumberOfDepartment;
    private String postcodeOfDepartment;
    private String cityOfDepartment;
    private String jobName;
}

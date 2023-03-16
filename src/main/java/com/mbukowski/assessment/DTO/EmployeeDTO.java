package com.mbukowski.assessment.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@JsonPropertyOrder({"name","surname","email","phoneNumber","dateOfEmployment","salary","streetOfEmployee","houseNumberOfEmployee"
        ,"postcodeOfEmployee","cityOfEmployee","departmentName","streetOfDepartment","houseNumberOfDepartment","postcodeOfDepartment"
        ,"cityOfDepartment","jobName"})
public class EmployeeDTO {
    private String name;
    private String surname;
    private String email;
    private Integer phoneNumber;
    @JsonFormat(pattern="yyyy/MM/dd")
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

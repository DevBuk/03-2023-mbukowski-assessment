package com.mbukowski.assessment.DTO;

import lombok.*;

import java.math.BigDecimal;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AverageSalaryByPositionAndSeniorityOfEmployeesDTO {
    private String jobName;
    private Integer seniority;
    private BigDecimal averageSalary;


}

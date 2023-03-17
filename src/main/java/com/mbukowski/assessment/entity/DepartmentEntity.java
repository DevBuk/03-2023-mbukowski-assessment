package com.mbukowski.assessment.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Dzialy")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Length(max = 30)
    @Column(name = "nazwa")
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "lokalizacja")
    private AddressEntity addressEntity;

    @OneToMany
    private List<EmployeeEntity> employees = new ArrayList<>();

    public DepartmentEntity(String departmentName, AddressEntity addressEntity) {
        this.departmentName = departmentName;
        this.addressEntity = addressEntity;
    }
}

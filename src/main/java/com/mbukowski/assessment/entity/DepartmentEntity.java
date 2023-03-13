package com.mbukowski.assessment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @Column(name = "nazwa")
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "lokalizacja")
    private AddressEntity addressEntity;

    @JsonIgnore
    @OneToMany
    private List<EmployeeEntity> employees = new ArrayList<>();

    public DepartmentEntity(String departmentName, AddressEntity addressEntity) {
        this.departmentName = departmentName;
        this.addressEntity = addressEntity;
    }
}

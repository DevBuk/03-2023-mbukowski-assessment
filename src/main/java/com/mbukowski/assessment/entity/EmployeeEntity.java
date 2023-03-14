package com.mbukowski.assessment.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Pracownicy")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "imie")
    private String name;
    @Column(name = "nazwisko")
    private String surname;
    private String email;
    @Column(name = "telefon")
    private Integer phoneNumber;
    @Column(name = "dataZatrudnienia")
    private LocalDate dateOfEmployment;
    @Column(name = "wynagrodzenie")
    private BigDecimal salary;
    @OneToOne
    @JoinColumn(name = "Adres")
    private AddressEntity addressEntity;
    @ManyToOne
    @JoinColumn(name = "Dzial")
    private DepartmentEntity departmentEntity;
    @ManyToOne
    @JoinColumn(name = "Stanowisko")
    private PositionEntity positionEntity;

    public EmployeeEntity(String name, String surname, String email, Integer phoneNumber, LocalDate dateOfEmployment, BigDecimal salary, AddressEntity addressEntity, DepartmentEntity departmentEntity, PositionEntity positionEntity) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfEmployment = dateOfEmployment;
        this.salary = salary;
        this.addressEntity = addressEntity;
        this.departmentEntity = departmentEntity;
        this.positionEntity = positionEntity;
    }

}

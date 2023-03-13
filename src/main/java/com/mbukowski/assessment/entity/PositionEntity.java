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
@Table(name = "Stanowiska")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nazwa")
    private String jobName;
    @JsonIgnore
    @OneToMany
    private List<EmployeeEntity> employees = new ArrayList<>();

    public PositionEntity(String jobName){
        this.jobName = jobName;
    }
}

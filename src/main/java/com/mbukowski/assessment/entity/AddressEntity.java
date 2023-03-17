package com.mbukowski.assessment.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "Adresy")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 30)
    @Column(name = "ulica")
    private String street;
    @Length(max = 10)
    @Column(name = "nrDomu")
    private String houseNumber;
    @Length(max = 6)
    @Column(name = "kodPocztowy")
    private String postcode;
    @NotBlank
    @Length(max = 30)
    @Column(name = "miasto")
    private String city;

    public AddressEntity(String street, String houseNumber, String postcode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
    }
}
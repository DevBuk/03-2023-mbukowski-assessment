package com.mbukowski.assessment.entity;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "ulica")
    private String street;
    @Column(name = "nrDomu")
    private String houseNumber;
    @Column(name = "kodPocztowy")
    private String postcode;
    @Column(name = "miasto")
    private String city;

    public AddressEntity(String street, String houseNumber, String postcode, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
    }
}

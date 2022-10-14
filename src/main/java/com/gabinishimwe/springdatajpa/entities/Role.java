package com.gabinishimwe.springdatajpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
//    @SequenceGenerator(
//            name = "role_id_sequence",
//            sequenceName = "role_id_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//        strategy = GenerationType.SEQUENCE,
//        generator = "role_id_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            name = "role_name"
    )
    private String roleName;

}

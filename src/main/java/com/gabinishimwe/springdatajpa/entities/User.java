package com.gabinishimwe.springdatajpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
//    private String role;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "role_id",
            referencedColumnName = "id"
    )
    private List<Role> role;
    private boolean enabled=false;

    public User(Object o, String gabin, String ishimwe, String s, Object o1, boolean b) {
    }
}

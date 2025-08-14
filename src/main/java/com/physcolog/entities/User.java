package com.physcolog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor // ✅ Default constructor ekleniyor!
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(nullable = false,length = 25)
   private String firstName;

    @Column(nullable = false,length = 25)
   private String lastName;

    @Column(nullable = false,length = 25,unique = true)
   private String userName;

    @Column(nullable = false,length = 255)
   private String password;

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "role_id"),
                                  inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles=new HashSet<>();

}

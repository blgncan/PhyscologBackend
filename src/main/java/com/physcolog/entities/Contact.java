package com.physcolog.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,length = 20)
    String phone;

    @Column(nullable = false,length = 60)
    String email;

    @Column(nullable = false,length = 100)
    String location;
}

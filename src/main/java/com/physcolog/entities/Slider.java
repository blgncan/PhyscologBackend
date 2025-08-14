package com.physcolog.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "slider")
@RequiredArgsConstructor
@Data
public class Slider {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,length=100)
    String title;

    @Column(nullable = false,length=130)
    String description;

    @Column(nullable = false)
    String photo;
}

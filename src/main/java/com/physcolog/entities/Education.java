package com.physcolog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,length = 50)
    String title;

    @Column(nullable = false,length = 20)
    String date;

    @ManyToOne
    @JoinColumn(name = "about_id", nullable = false)
    @JsonBackReference
    private About about;
}

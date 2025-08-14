package com.physcolog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "academic")
public class Academic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false,length = 20)
    private String date;

    @ManyToOne
    @JoinColumn(name = "about_id", nullable = false)
    @JsonBackReference
    private About about;
}

package com.physcolog.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Text;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "clinics")
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,length = 100)
    String title;


    @Column(nullable = false,length = 115)
    String summary;

    @Column(columnDefinition = "TEXT", nullable = false)
    String detail;
}

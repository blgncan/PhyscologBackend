package com.physcolog.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "about")
public class About {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        @Column(nullable = false,length=75)
        String namesurname;
        @Column(nullable = false,length = 200)
        String jobtitle;
        @Column(nullable = false)
        String photo;

        @Column(columnDefinition = "TEXT", nullable = false,length = 750)
        String details;

        @OneToMany(mappedBy = "about", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Education> educationList = new ArrayList<>();

        @OneToMany(mappedBy = "about", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Academic> academicList = new ArrayList<>();

        @OneToMany(mappedBy = "about", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Courses> courseList = new ArrayList<>();

}

package com.physcolog.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@RequiredArgsConstructor
@Table(name = "contact_form")
@Data
public class ContactForm extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,length = 30)
    String name;

    @Column(nullable = false,length = 60)
    String email;

    @Column(nullable = false,length = 100)
    String subject;

    @Column(nullable = false,length = 500)
    String message;

    @Column(nullable = false,name = "is_approved", columnDefinition = "BOOLEAN DEFAULT FALSE")
    boolean isApproved = false; // Varsayılan olarak false (yayınlanmamış)

}

package com.physcolog.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Entity
@Table(name = "randevu")
public class RandevuForm extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    @Column(nullable = false,length = 30)
    String name;

    @Column(nullable = false,length = 60)
    String email;

    @Column(nullable = false,length = 30)
    String phone;

    @Column(nullable = false)
    Integer age;

    @Column(nullable = false,length = 50)
    String parents;

    @Column(nullable = false,length = 200)
    String medicine;

    @Column(nullable = false,length = 10)
    String gender;

    @Column(nullable = false,length = 500)
    String problem;

}

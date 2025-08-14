package com.physcolog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Column(nullable = false, updatable = false)
    private String createdDate;

    @PrePersist
    protected void onCreate() {
        // DateTime Formatı oluşturduk
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        // Şu anki tarihi ve saati formatlayarak 'createdDate' alanına atadık
        this.createdDate = LocalDateTime.now().format(formatter);
    }
}

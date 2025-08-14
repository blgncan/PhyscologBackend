package com.physcolog.repository;

import com.physcolog.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
    @Query(value = "SELECT * FROM contact ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Contact findLatestOneContact();
}

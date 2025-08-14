package com.physcolog.repository;

import com.physcolog.entities.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AboutRepository extends JpaRepository<About,Long> {
Optional<About> findFirstByOrderByIdDesc();

    @Query(value = "SELECT * FROM about ORDER BY id DESC LIMIT 1", nativeQuery = true)
    List<About> findLatestAbout();



}

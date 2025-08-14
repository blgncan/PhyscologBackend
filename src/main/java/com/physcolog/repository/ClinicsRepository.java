package com.physcolog.repository;

import com.physcolog.entities.Clinics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClinicsRepository extends JpaRepository<Clinics,Long> {
    @Query(value = "SELECT * FROM clinics ORDER BY id DESC LIMIT 6", nativeQuery = true)
    List<Clinics> findLatestSixClinics();
}

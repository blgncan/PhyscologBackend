package com.physcolog.repository;

import com.physcolog.entities.RandevuForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandevuFormRepository extends JpaRepository<RandevuForm,Long> {
}

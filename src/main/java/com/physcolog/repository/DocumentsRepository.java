package com.physcolog.repository;

import com.physcolog.entities.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents,Long> {
    @Query(value = "SELECT * FROM documents ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Documents getLatestOneDocuments();
}

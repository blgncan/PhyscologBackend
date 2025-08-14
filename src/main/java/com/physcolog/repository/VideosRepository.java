package com.physcolog.repository;

import com.physcolog.entities.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideosRepository extends JpaRepository<Videos,Long> {
    @Query(value = "SELECT * FROM videos ORDER BY id DESC LIMIT 4", nativeQuery = true)
    List<Videos> getLatestFourVideos();

    @Query("SELECT v FROM Videos v WHERE LOWER(v.category) = LOWER(:videoCategory)")
    List<Videos> findByCategory(@Param("videoCategory") String videoCategory);


}

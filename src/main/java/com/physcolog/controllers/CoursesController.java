package com.physcolog.controllers;

import com.physcolog.dto.AcademicRequest;
import com.physcolog.dto.CoursesRequest;
import com.physcolog.entities.Academic;
import com.physcolog.entities.Courses;
import com.physcolog.services.CoursesServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CoursesController {
private final CoursesServices coursesServices;
    @PostMapping
    public ResponseEntity<Courses> saveCourses(@RequestBody CoursesRequest request) {
        Courses savedCourses = coursesServices.saveCourses(request);
        return ResponseEntity.ok(savedCourses);
    }

    @GetMapping("/all")
    public List<Courses> getAllCourses(){
        return coursesServices.getAllCourses();
    }
    @GetMapping("/{coursesId}")
    public Optional<Courses> getCoursesById(@PathVariable Long coursesId){
        return coursesServices.getCoursesById(coursesId);
    }
    @DeleteMapping("/{coursesId}")
    public Courses deleteCourses(@PathVariable Long coursesId){
        return coursesServices.deleteCourses(coursesId);
    }
    @PutMapping("/{coursesId}")
    public ResponseEntity<Courses> updateCourses(@RequestBody Courses courses,@PathVariable Long coursesId){
        Courses updateCourses= coursesServices.updateCourses(courses,coursesId);
        return ResponseEntity.ok(updateCourses);
    }
}

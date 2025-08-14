package com.physcolog.controllers;

import com.physcolog.dto.AcademicRequest;
import com.physcolog.dto.EducationRequest;
import com.physcolog.entities.Academic;
import com.physcolog.entities.Education;
import com.physcolog.services.AcademicService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/academic")
public class AcademicController {
    private final AcademicService academicService;

    @PostMapping
    public ResponseEntity<Academic> saveAcademic(@RequestBody AcademicRequest request) {
        Academic savedAcademic = academicService.saveAcademic(request);
        return ResponseEntity.ok(savedAcademic);
    }

    @GetMapping("/all")
    public List<Academic> getAllEducation(){
        return academicService.getAllAcademic();
    }
    @GetMapping("/{academicId}")
    public Optional<Academic> getAcademicById(@PathVariable Long academicId){
        return academicService.getAcademicById(academicId);
    }
    @DeleteMapping("/{academicId}")
    public Academic deleteAcademic(@PathVariable Long academicId){
        return academicService.deleteAcademic(academicId);
    }
    @PutMapping("/{academicId}")
    public ResponseEntity<Academic> updateAcademic(@RequestBody Academic academic,@PathVariable Long academicId){
        Academic updateAcademic= academicService.updateAcademic(academic,academicId);
        return ResponseEntity.ok(updateAcademic);
    }
}

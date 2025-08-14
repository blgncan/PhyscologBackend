package com.physcolog.controllers;

import com.physcolog.dto.EducationRequest;
import com.physcolog.entities.About;
import com.physcolog.entities.Education;
import com.physcolog.services.EducationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/education")
public class EducationController {
    private final EducationService educationService;
    @PostMapping
    public ResponseEntity<Education> saveEducation(@RequestBody EducationRequest request) {
       // Service katmanına gönder
        Education savedEducation = educationService.saveEducation(request);

        return ResponseEntity.ok(savedEducation);
    }
    @GetMapping("/all")
    public List<Education> getAllEducation(){
        return educationService.getAllEducation();
    }
    @GetMapping("/{educationId}")
    public Optional<Education> getEducationById(@PathVariable Long educationId){
        return educationService.getEducationById(educationId);
    }
    @DeleteMapping("/{educationId}")
    public Education deleteEducation(@PathVariable Long educationId){
        return educationService.deleteEducation(educationId);
    }
    @PutMapping("/{educationId}")
    public ResponseEntity<Education> updateEducation(@RequestBody Education education,@PathVariable Long educationId){
       Education updateEducation= educationService.updateEducation(education,educationId);
       return ResponseEntity.ok(updateEducation);
    }
}

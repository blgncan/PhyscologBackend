package com.physcolog.controllers;

import com.physcolog.entities.About;
import com.physcolog.entities.Clinics;
import com.physcolog.services.ClinicsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/clinics")
public class ClinicsController {
    private final ClinicsService clinicsService;
    @PostMapping
    //Burda kayıt yapıp Frontentin işine yarasın diye, geriye Entitynin tamamını döndürüyoruz.
    public ResponseEntity<Clinics> saveClinics(@RequestBody Clinics clinics){
        Clinics savedClinics=clinicsService.savedClinics(clinics);
        return ResponseEntity.ok(savedClinics);
    }
    @PutMapping("/{clinicId}")
    public ResponseEntity<Clinics> updatedClinics(@RequestBody Clinics clinics,@PathVariable long clinicId){
        Clinics updatedClinics=clinicsService.updatedClinics(clinics,clinicId);
        return ResponseEntity.ok(updatedClinics);

    }
    @GetMapping("/all")
    public List<Clinics> getAllClinics(){
        return clinicsService.getAllClinics();
    }
    @GetMapping("/{clinicId}")
    public Optional<Clinics> getClinicsById(@PathVariable Long clinicId){
      return clinicsService.getClinicById(clinicId);
    }
    @GetMapping("/latest-six")
    public List<Clinics> getLatestSixClinic(){
    return clinicsService.getLatestSixClinic();
    }

    @GetMapping("/all-clinics")
    public List<Clinics> getAllClinic(){
        return clinicsService.getAllClinic();
    }
    @DeleteMapping("/{clinicId}")
    public Clinics deletedClinic(@PathVariable long clinicId){
        return clinicsService.deleteClinicById(clinicId);
    }
}

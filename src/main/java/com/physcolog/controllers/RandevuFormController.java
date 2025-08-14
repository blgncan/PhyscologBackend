package com.physcolog.controllers;

import com.physcolog.entities.RandevuForm;
import com.physcolog.services.RandevuFormServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/randevu")
public class RandevuFormController {
    private final RandevuFormServices randevuFormServices;
    @PostMapping
    public ResponseEntity<RandevuForm> createRandevuForm(@RequestBody RandevuForm randevuForm){
      RandevuForm savedRandevu=  randevuFormServices.createRandevuForm(randevuForm);
      return ResponseEntity.ok(savedRandevu);
    }
    @GetMapping("/all")
    public List<RandevuForm> getAllRandevuForm(){
        return randevuFormServices.getAllRandevuForm();
    }
    @GetMapping("/{randevuId}")
    public Optional<RandevuForm> getRandevuFormById(@PathVariable Long randevuId){
        return randevuFormServices.getRandevuFormById(randevuId);
    }
    @DeleteMapping("/{randevuId}")
    public RandevuForm deleteRandevuForm(@PathVariable Long randevuId){
        return randevuFormServices.deleteRandevuForm(randevuId);
    }
    @PutMapping("/{randevuId}")
    public ResponseEntity<RandevuForm> updateRandevuForm(@PathVariable Long randevuId,@RequestBody RandevuForm randevuForm){
      RandevuForm updatedRandevuForm= randevuFormServices.updateRandevuForm(randevuId,randevuForm);
      return ResponseEntity.ok(updatedRandevuForm);
    }

}

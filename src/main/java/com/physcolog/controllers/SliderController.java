package com.physcolog.controllers;

import com.physcolog.entities.About;
import com.physcolog.entities.Slider;
import com.physcolog.services.SliderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/slider")
public class SliderController {
    private final SliderService sliderService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createNewSlider(
      @RequestParam("title") String title,
      @RequestParam("description") String description,
      @RequestParam("photo") MultipartFile photo

    ){
        Slider createdSlider= null;
        try {
            createdSlider = sliderService.createNewSlider(title,description,photo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(createdSlider);

    }

    @GetMapping //localhost:8080/slider
    public List<Slider> getAllSliderShorByDesc(){
        return sliderService.getAllSliderShorByDesc();
    }

    @GetMapping("/{sliderId}")//localhost:8080/slider/4
    public Optional<Slider> getSliderById(@PathVariable Long sliderId){
        return sliderService.getSliderById(sliderId);
    }


    @PutMapping("/{sliderId}") //localhost:8080/slider/4
    public ResponseEntity<?> updateSlider(
            @PathVariable Long sliderId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("photo") MultipartFile photo

    ){
        try {
            Slider updatedSlider = sliderService.updateSliderById(sliderId,title, description, photo);
            return ResponseEntity.ok(updatedSlider);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dosya işleme hatası!");
        }


    }

    @DeleteMapping("/{sliderId}") //localhost:8080/slider/4
    public Slider deleteOneSlider(@PathVariable Long sliderId){
        return sliderService.deleteById(sliderId);

    }


}

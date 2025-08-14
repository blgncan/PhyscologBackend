package com.physcolog.controllers;

import com.physcolog.entities.About;
import com.physcolog.services.AboutService;
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
@RequestMapping("/about")
@RequiredArgsConstructor
@Data

public class AboutController {

    private final AboutService aboutService;
    @GetMapping //localhost:8080/about
    //Ana Sayfaya çekerken bu kısmı kullanacağız.En son kaydedilen About datasını çekeceğiz.
    public ResponseEntity<About> getLatestAboutById(){
        return ResponseEntity.ok(aboutService.getLatestAboutById());
    }

    @GetMapping("/{aboutId}")//localhost:8080/about/4
    public Optional<About> getAboutById(@PathVariable Long aboutId){
       return aboutService.getAboutById(aboutId);
    }
    @GetMapping("/all") //localhost:8080/about/all
    //Admin sayfasında tüm kayıtlı aboutları görüntülemek için bu kısmı kullanacağız.
    public List<About> getAllAbouts(){
        return aboutService.getAllAbout();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAbout(
            @RequestParam("namesurname") String namesurname,
            @RequestParam("jobtitle") String jobtitle,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("details") String details
    ) {

        try {
            About savedAbout = aboutService.saveNewAbout(namesurname, jobtitle, photo, details);
            return ResponseEntity.ok(savedAbout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dosya işleme hatası!");
        }
    }

    @PutMapping("/{aboutId}") //localhost:8080/about/4
    public ResponseEntity<?> updateAbout(
            @PathVariable Long aboutId,
            @RequestParam("namesurname") String namesurname,
            @RequestParam("jobtitle") String jobtitle,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("details") String details
    ){
        try {
            About updatedAbout = aboutService.updateAboutById(aboutId,namesurname, jobtitle, photo, details);
            return ResponseEntity.ok(updatedAbout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Dosya işleme hatası!");
        }


    }

    @DeleteMapping("/{aboutId}") //localhost:8080/about/4
    public About deleteOneAbout(@PathVariable Long aboutId){
      return aboutService.deleteById(aboutId);
    }
}

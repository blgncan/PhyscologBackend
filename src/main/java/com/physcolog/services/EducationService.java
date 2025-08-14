package com.physcolog.services;

import com.physcolog.dto.EducationRequest;
import com.physcolog.entities.About;
import com.physcolog.entities.Education;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.AboutRepository;
import com.physcolog.repository.EducationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class EducationService {
    private final EducationRepository educationRepository;
    private final AboutRepository aboutRepository;

    public Education saveEducation(EducationRequest request) {
        // Validasyonlar
        if (!StringUtils.hasText(request.getTitle())) {
            throw new ValidationException("Lütfen başlık kısmını boş geçmeyiniz..!");
        }
        if (!StringUtils.hasText(request.getDate())) {
            throw new ValidationException("Lütfen tarihi boş geçmeyiniz");
        }

        // About nesnesini çekiyoruz
        About about = aboutRepository.findById(request.getAbout_id())
                .orElseThrow(() -> new RuntimeException("About bulunamadı: " + request.getAbout_id()));

        // **DTO'dan Entity'ye dönüştürme**
        Education education = new Education();
        education.setTitle(request.getTitle());
        education.setDate(request.getDate());
        education.setAbout(about); // **About nesnesini ekledik!**

        // **Veritabanına kaydediyoruz ve Education nesnesini geri döndürüyoruz**
        return educationRepository.save(education);
    }

    public List<Education> getAllEducation() {
        return educationRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public Optional<Education> getEducationById(Long educationId) {
       Optional<Education> foundedEducation= educationRepository.findById(educationId);
       if (foundedEducation.isPresent()){
           return foundedEducation;
       }else throw new RuntimeException("Kayıt Bulunamadı..!");
    }

    public Education deleteEducation(Long educationId) {
       Optional<Education> foundedEducation= educationRepository.findById(educationId);
       if (foundedEducation.isPresent()){
           Education deletedEducation=foundedEducation.get();
           educationRepository.deleteById(educationId);
           return deletedEducation;
       }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }

    public Education updateEducation(Education education, Long educationId) {
      Optional<Education> foundEducation= educationRepository.findById(educationId);

      if (foundEducation.isPresent()){

          Education foundedEducation=foundEducation.get();

          foundedEducation.setTitle(education.getTitle());
          foundedEducation.setDate(education.getDate());
          educationRepository.save(foundedEducation);
          return foundedEducation;
      }throw new RuntimeException("Böyle bir kayıt bulunamadı..!");

    }

}

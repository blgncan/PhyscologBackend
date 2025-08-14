package com.physcolog.services;

import com.physcolog.dto.AcademicRequest;
import com.physcolog.entities.About;
import com.physcolog.entities.Academic;
import com.physcolog.entities.Education;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.AboutRepository;
import com.physcolog.repository.AcademicRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class AcademicService {
    private final AcademicRepository academicRepository;
    private final AboutRepository aboutRepository;

    public Academic saveAcademic(AcademicRequest request) {
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
        Academic academic = new Academic();
        academic.setTitle(request.getTitle());
        academic.setDate(request.getDate());
        academic.setAbout(about); // **About nesnesini ekledik!**

        // **Veritabanına kaydediyoruz ve Academic nesnesini geri döndürüyoruz**
        return academicRepository.save(academic);
    }
    public List<Academic> getAllAcademic() {
        return academicRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));    }
    public Optional<Academic> getAcademicById(Long academicId) {
        Optional<Academic> foundedAcademic= academicRepository.findById(academicId);
        if (foundedAcademic.isPresent()){
            return foundedAcademic;
        }else throw new RuntimeException("Kayıt Bulunamadı..!");
    }

    public Academic deleteAcademic(Long academicId) {
        Optional<Academic> foundedAcademic= academicRepository.findById(academicId);
        if (foundedAcademic.isPresent()){
            Academic deletedAcademic=foundedAcademic.get();
            academicRepository.deleteById(academicId);
            return deletedAcademic;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }

    public Academic updateAcademic(Academic academic, Long academicId) {
        Optional<Academic> foundAcademic= academicRepository.findById(academicId);

        if (foundAcademic.isPresent()){

            Academic foundedAcademic=foundAcademic.get();

            foundedAcademic.setTitle(academic.getTitle());
            foundedAcademic.setDate(academic.getDate());
            academicRepository.save(foundedAcademic);
            return foundedAcademic;
        }throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }
}

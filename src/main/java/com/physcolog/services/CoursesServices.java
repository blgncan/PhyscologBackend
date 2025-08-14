package com.physcolog.services;

import com.physcolog.dto.CoursesRequest;
import com.physcolog.entities.About;
import com.physcolog.entities.Academic;
import com.physcolog.entities.Courses;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.AboutRepository;
import com.physcolog.repository.CoursesRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class CoursesServices {
    private final CoursesRepository coursesRepository;
    private final AboutRepository aboutRepository;

    public Courses saveCourses(CoursesRequest request) {
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
        Courses courses = new Courses();
        courses.setTitle(request.getTitle());
        courses.setDate(request.getDate());
        courses.setAbout(about); // **About nesnesini ekledik!**

        // **Veritabanına kaydediyoruz ve Courses nesnesini geri döndürüyoruz**
        return coursesRepository.save(courses);
    }

    public List<Courses> getAllCourses() {
        return coursesRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    public Optional<Courses> getCoursesById(Long coursesId) {
        Optional<Courses> foundedCourses= coursesRepository.findById(coursesId);
        if (foundedCourses.isPresent()){
            return foundedCourses;
        }else throw new RuntimeException("Kayıt Bulunamadı..!");
    }


    public Courses deleteCourses(Long coursesId) {
        Optional<Courses> foundedCourses= coursesRepository.findById(coursesId);
        if (foundedCourses.isPresent()){
            Courses deletedCourses=foundedCourses.get();
            coursesRepository.deleteById(coursesId);
            return deletedCourses;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }

    public Courses updateCourses(Courses courses, Long coursesId) {
        Optional<Courses> foundCourses= coursesRepository.findById(coursesId);

        if (foundCourses.isPresent()){

            Courses foundedCourses=foundCourses.get();

            foundedCourses.setTitle(courses.getTitle());
            foundedCourses.setDate(courses.getDate());
            coursesRepository.save(foundedCourses);
            return foundedCourses;
        }throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }
}

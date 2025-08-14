package com.physcolog.services;

import com.physcolog.entities.About;
import com.physcolog.entities.Clinics;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.ClinicsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Data
@Service

public class ClinicsService {
    private final ClinicsRepository clinicsRepository;

    public Clinics savedClinics(Clinics clinics) {
        //Boş alan kontrolü yapıyoruz.
        if (!StringUtils.hasText(clinics.getTitle())){
            throw  new ValidationException("Lütfen başlık bölümünü boş geçmeyiniz..!");
        }
        if(!StringUtils.hasText(clinics.getSummary())){
            throw new ValidationException("Lütfen özet bölümünü boş geçmeyiniz");
        }
        if (!StringUtils.hasText(clinics.getDetail())){
            throw new ValidationException("Lütfen detay bölümünü boş geçmeyiniz");
        }
        //Kayıt yapıyoruz
        return clinicsRepository.save(clinics);
    }

    public Clinics updatedClinics(Clinics clinics, long clinicId) {
        //O id ye ait datanın olmama ihtimaline karşı optional yapıyoruz ve aşağıdaki if ile onu kontrol ediyoruz.
        Optional<Clinics> clinic=clinicsRepository.findById(clinicId);
        if (clinic.isPresent()){
            Clinics foundedClinic=clinic.get();
            //Şimdi güncellemeyi yapıyoruz.
            foundedClinic.setTitle(clinics.getTitle());
            foundedClinic.setSummary(clinics.getSummary());
            foundedClinic.setDetail(clinics.getDetail());
            clinicsRepository.save(foundedClinic);
            return foundedClinic;
        }
        else throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }


    public List<Clinics> getAllClinics() {
        return clinicsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Optional<Clinics> getClinicById(Long clinicId) {
        Optional<Clinics> foundedClinic=clinicsRepository.findById(clinicId);
        if(foundedClinic.isPresent()){
            return foundedClinic;
        }else throw new RuntimeException("Kayıt Bulunamadı");
    }

    public Clinics deleteClinicById(long clinicId) {
        Optional<Clinics> clinic=clinicsRepository.findById(clinicId);
        if(clinic.isPresent()){
            Clinics deletedClinic=clinic.get();
            clinicsRepository.deleteById(clinicId);
            return deletedClinic;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }

    public List<Clinics> getLatestSixClinic() {
        return clinicsRepository.findLatestSixClinics();
    }
    public List<Clinics> getAllClinic() {
        return clinicsRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
}

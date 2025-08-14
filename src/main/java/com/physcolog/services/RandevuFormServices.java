package com.physcolog.services;

import com.physcolog.entities.Contact;
import com.physcolog.entities.RandevuForm;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.RandevuFormRepository;
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
public class RandevuFormServices {
    private final RandevuFormRepository randevuFormRepository;
    public RandevuForm createRandevuForm(RandevuForm randevuForm) {
        //Boş alan kontrolü yapıyoruz.
        if (!StringUtils.hasText(randevuForm.getName())){
            throw  new ValidationException("Lütfen adınızı ve soyadınız boş geçmeyiniz..!");
        }
        if(!StringUtils.hasText(randevuForm.getEmail())){
            throw new ValidationException("Lütfen e-mail adresinizi boş geçmeyiniz");
        }
        if (!StringUtils.hasText(randevuForm.getPhone())){
            throw new ValidationException("Lütfen telefon numaranızı boş geçmeyiniz");
        }
        if (randevuForm.getAge() == null){
            throw new ValidationException("Lütfen yaşınızı boş geçmeyiniz");
        }
        if (randevuForm.getAge() <= 0) {
            throw new ValidationException("Lütfen geçerli bir yaş giriniz.");
        }
        if (randevuForm.getAge() > 100) {
            throw new ValidationException("Lütfen geçerli bir yaş giriniz.");
        }
      return randevuFormRepository.save(randevuForm);
    }
    public List<RandevuForm> getAllRandevuForm() {
        return randevuFormRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
    public Optional<RandevuForm> getRandevuFormById(Long randevuId) {
        Optional<RandevuForm> foundedRandevu=randevuFormRepository.findById(randevuId);
        if (foundedRandevu.isPresent()){
            return foundedRandevu;
        }else throw new RuntimeException("Kayıt Bulunamadı..!");
    }
    public RandevuForm deleteRandevuForm(Long randevuId) {
        Optional<RandevuForm> randevuForm=randevuFormRepository.findById(randevuId);
        if (randevuForm.isPresent()){
            RandevuForm deletedRandevuForm=randevuForm.get();
            randevuFormRepository.deleteById(randevuId);
            return deletedRandevuForm;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }
    public RandevuForm updateRandevuForm(Long randevuId, RandevuForm randevuForm) {

        //Boş alan kontrolü yapıyoruz.
        if (!StringUtils.hasText(randevuForm.getName())){
            throw  new ValidationException("Lütfen adınızı ve soyadınız boş geçmeyiniz..!");
        }
        if(!StringUtils.hasText(randevuForm.getEmail())){
            throw new ValidationException("Lütfen e-mail adresinizi boş geçmeyiniz");
        }
        if (!StringUtils.hasText(randevuForm.getPhone())){
            throw new ValidationException("Lütfen telefon numaranızı boş geçmeyiniz");
        }
        if (randevuForm.getAge() == null){
            throw new ValidationException("Lütfen yaşınızı boş geçmeyiniz");
        }
        if (randevuForm.getAge() <= 0) {
            throw new ValidationException("Lütfen geçerli bir yaş giriniz.");
        }
        if (randevuForm.getAge() > 100) {
            throw new ValidationException("Lütfen geçerli bir yaş giriniz.");
        }

        //O id ye ait datanın olmama ihtimaline karşı optional yapıyoruz ve aşağıdaki if ile onu kontrol ediyoruz.
        Optional<RandevuForm> foundRandevu=randevuFormRepository.findById(randevuId);
        if (foundRandevu.isPresent()){
            RandevuForm foundedRandevuForm=foundRandevu.get();
            //Şimdi güncellemeyi yapıyoruz.
            foundedRandevuForm.setPhone(randevuForm.getPhone());
            foundedRandevuForm.setEmail(randevuForm.getEmail());
            foundedRandevuForm.setAge(randevuForm.getAge());
            foundedRandevuForm.setName(randevuForm.getName());
            foundedRandevuForm.setGender(randevuForm.getGender());
            foundedRandevuForm.setMedicine(randevuForm.getMedicine());
            foundedRandevuForm.setProblem(randevuForm.getProblem());
            randevuFormRepository.save(foundedRandevuForm);
            return foundedRandevuForm;
        }
        else throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }
}

package com.physcolog.services;
import com.physcolog.entities.ContactForm;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.ContactFormRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class ContactFormService {
    private final ContactFormRepository contactFormRepository;
    public ContactForm saveContactForm(ContactForm contactForm) {
        //Boş alan kontrolü yapıyoruz.
        if (!StringUtils.hasText(contactForm.getName())){
            throw  new ValidationException("Lütfen adınızı ve soyadınızı boş geçmeyiniz..!");
        }
        if(!StringUtils.hasText(contactForm.getEmail())){
            throw new ValidationException("Lütfen e-mail adresinizi boş geçmeyiniz");
        }
        if (!StringUtils.hasText(contactForm.getSubject())){
            throw new ValidationException("Lütfen konuyu boş geçmeyiniz");
        }
        if (!StringUtils.hasText(contactForm.getMessage())){
            throw new ValidationException("Lütfen mesaj bölümünü boş geçmeyiniz");
        }

        //Kayıt yapıyoruz
        return contactFormRepository.save(contactForm);
    }
    public Page<ContactForm> getAllContactForm(Pageable pageable) {
        return contactFormRepository.getAllApprovedContactForms(pageable);
    }

    public Page<ContactForm> getIsApprovedmessages(Pageable pageable) {
        return contactFormRepository.findAll(pageable);
    }
    public Optional<ContactForm> getContactFormById(Long contactFormId) {
        Optional<ContactForm> foundedContactForm=contactFormRepository.findById(contactFormId);
        if(foundedContactForm.isPresent()){
            return foundedContactForm;
        }else throw new RuntimeException("Kayıt Bulunamadı");
    }
    public ContactForm deleteContactFormById(Long contactFormId) {
       Optional<ContactForm> contactForm= contactFormRepository.findById(contactFormId);
       if (contactForm.isPresent()){
          ContactForm deletedContactForm=contactForm.get();
          contactFormRepository.deleteById(contactFormId);
          return deletedContactForm;
       }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }
    public ContactForm updateContactFormById(ContactForm contactForm, long contactFormId) {
        //O id ye ait datanın olmama ihtimaline karşı optional yapıyoruz ve aşağıdaki if ile onu kontrol ediyoruz.
        Optional<ContactForm> foundcontactForm=contactFormRepository.findById(contactFormId);
        if (foundcontactForm.isPresent()){
            ContactForm foundedContactForm=foundcontactForm.get();
            //Şimdi güncellemeyi yapıyoruz.
            foundedContactForm.setName(contactForm.getName());
            foundedContactForm.setEmail(contactForm.getEmail());
            foundedContactForm.setSubject(contactForm.getSubject());
            foundedContactForm.setMessage(contactForm.getMessage());
            contactFormRepository.save(foundedContactForm);
            return foundedContactForm;
        }
        else throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }
    public ResponseEntity<String> approveComment(Long contactFormId) {
     Optional<ContactForm> ContactForm=  contactFormRepository.findById(contactFormId);
     if (ContactForm.isPresent()){
         ContactForm form=ContactForm.get();
         form.setApproved(true);
         contactFormRepository.save(form);
         return ResponseEntity.ok("Yorum onaylandı!");
     }return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Yorum bulunamadı!");
    }
}

package com.physcolog.services;

import com.physcolog.entities.Contact;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.ContactRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class ContactService {
    private final ContactRepository contactRepository;
    public Contact saveContact(Contact contact) {
        //Boş alan kontrolü yapıyoruz.
        if (!StringUtils.hasText(contact.getPhone())){
            throw  new ValidationException("Lütfen telefon numaranızı boş geçmeyiniz..!");
        }
        if(!StringUtils.hasText(contact.getEmail())){
            throw new ValidationException("Lütfen e-mail adresinizi boş geçmeyiniz");
        }
        if (!StringUtils.hasText(contact.getLocation())){
            throw new ValidationException("Lütfen adresinizi boş geçmeyiniz");
        }
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public Optional<Contact> getContactById(Long contactId) {
     Optional<Contact> foundedContact=contactRepository.findById(contactId);
     if (foundedContact.isPresent()){
         return foundedContact;
     }else throw new RuntimeException("Kayıt Bulunamadı..!");
    }

    public Contact deleteContactById(Long contactId) {
        Optional<Contact> contact=contactRepository.findById(contactId);
        if(contact.isPresent()){
            Contact deletedContact=contact.get();
            contactRepository.deleteById(contactId);
            return deletedContact;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }

    public Contact updatedContact(Contact contact, long contactId) {

        //O id ye ait datanın olmama ihtimaline karşı optional yapıyoruz ve aşağıdaki if ile onu kontrol ediyoruz.
        Optional<Contact> foundcontact=contactRepository.findById(contactId);
        if (foundcontact.isPresent()){
            Contact foundedContact=foundcontact.get();
            //Şimdi güncellemeyi yapıyoruz.
            foundedContact.setPhone(contact.getPhone());
            foundedContact.setEmail(contact.getEmail());
            foundedContact.setLocation(contact.getLocation());
            contactRepository.save(foundedContact);
            return foundedContact;
        }
        else throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }

    public ResponseEntity<Contact> getLatestOneContact() {
        Contact latestContact = contactRepository.findLatestOneContact();
        if (latestContact != null) {
            return ResponseEntity.ok(latestContact); // Başarıyla döndürülen nesne
        } else {
            return ResponseEntity.notFound().build(); // Eğer sonuç bulunamazsa 404 döner
        }
    }
}

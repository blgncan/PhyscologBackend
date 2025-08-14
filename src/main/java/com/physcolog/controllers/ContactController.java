package com.physcolog.controllers;

import com.physcolog.entities.Clinics;
import com.physcolog.entities.Contact;
import com.physcolog.services.ContactService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Data
@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;
    @PostMapping
    //Contact Information bilgilerini kaydediyoruz.
    public ResponseEntity<Contact> saveContactInformation(@RequestBody Contact contact){
        Contact savedContact=contactService.saveContact(contact);
        return ResponseEntity.ok(savedContact);
    }
    @GetMapping("/all")
    //Tüm Contact Information bilgilerini çekiyoruz
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @GetMapping("/{contactId}")
    //Belirli bir id değerine göre Contact Information çekiyoruz.
    public Optional<Contact> getContactsById(@PathVariable Long contactId){
        return contactService.getContactById(contactId);
    }
    @GetMapping("/latest-one")
    public ResponseEntity<Contact> getLatestOneContact(){
        return contactService.getLatestOneContact();
    }

    @DeleteMapping("/{contactId}")
    //Id değerine göre silme işlemi yapıyoruz.
    public Contact deleteContactById(@PathVariable Long contactId){
        return contactService.deleteContactById(contactId);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Contact> updateContactById(@RequestBody Contact contact,@PathVariable long contactId){
        Contact updatedContact=contactService.updatedContact(contact,contactId);
        return ResponseEntity.ok(updatedContact);

    }


}

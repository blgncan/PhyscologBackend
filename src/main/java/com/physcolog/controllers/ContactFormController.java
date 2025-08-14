package com.physcolog.controllers;

import com.physcolog.entities.ContactForm;
import com.physcolog.services.ContactFormService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/contact-form")
public class ContactFormController {
    private final ContactFormService contactFormService;
    @PostMapping
    public ResponseEntity<ContactForm> saveContactForm(@RequestBody ContactForm contactForm){
       ContactForm savedContactForm= contactFormService.saveContactForm(contactForm);
       return ResponseEntity.ok(savedContactForm);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<ContactForm>> getAllContactForm(Pageable pageable){
        Page<ContactForm> allComments = contactFormService.getAllContactForm(pageable);
        return ResponseEntity.ok(allComments);
    }
    @GetMapping("/{contactFormId}")
    public Optional<ContactForm> getContactFormById(@PathVariable Long contactFormId){
        return contactFormService.getContactFormById(contactFormId);
    }

    @GetMapping("/isApproved")
    public ResponseEntity<Page<ContactForm>> getIsApprovedmessages(Pageable pageable){
        Page<ContactForm> approvedComments = contactFormService.getIsApprovedmessages(pageable);
        return ResponseEntity.ok(approvedComments);
    }
    @DeleteMapping("/{contactFormId}")
    public ContactForm deleteContactFormById(@PathVariable Long contactFormId){
        return contactFormService.deleteContactFormById(contactFormId);
    }

    @PutMapping("/isApproved/{contactFormId}")
    public ResponseEntity<String> approveComment(@PathVariable Long contactFormId){
        return contactFormService.approveComment(contactFormId);
    }
    @PutMapping("/{contactFormId}")
    public ResponseEntity<ContactForm> updateContactForm(@RequestBody ContactForm contactForm,@PathVariable long contactFormId){
        ContactForm updatedContactForm=contactFormService.updateContactFormById(contactForm,contactFormId);
        return ResponseEntity.ok(updatedContactForm);
    }
}

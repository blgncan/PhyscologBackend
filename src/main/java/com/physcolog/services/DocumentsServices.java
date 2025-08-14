package com.physcolog.services;

import com.physcolog.entities.Documents;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.DocumentsRepository;
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
public class DocumentsServices {
    private final DocumentsRepository documentsRepository;
    public Documents saveDocuments(Documents documents) {
        //Boş alan kontrolü yapıyoruz.
        if (!StringUtils.hasText(documents.getDocuments())){
            throw  new ValidationException("Lütfen belgeleri yazınız..!");
        }
        return documentsRepository.save(documents);

    }
    public List<Documents> getAllDocuments() {
        return documentsRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
    public Optional<Documents> getDocumentsBtId(Long documentsId) {

     Optional<Documents>  foundDocuments= documentsRepository.findById(documentsId);
     if (foundDocuments.isPresent()){
         return foundDocuments;
     }else throw new RuntimeException("Kayıt Bulunamadı..!");
    }
    public ResponseEntity<Documents> getLatestOneDocuments() {
      Documents latestDocuments=  documentsRepository.getLatestOneDocuments();
        if (latestDocuments != null) {
            return ResponseEntity.ok(latestDocuments); // Başarıyla döndürülen nesne
        } else {
            return ResponseEntity.notFound().build(); // Eğer sonuç bulunamazsa 404 döner
        }
    }
    public Documents deleteDocuments(Long documentsId) {
       Optional<Documents> documents= documentsRepository.findById(documentsId);
       if (documents.isPresent()){
           Documents deletedDocument=documents.get();
           documentsRepository.deleteById(documentsId);
           return deletedDocument;
       }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }
    public Documents updateDocuments(Long documentsId, Documents documents) {
       Optional<Documents> document= documentsRepository.findById(documentsId);
       if (document.isPresent()){
           Documents foundedDocuments=document.get();
           foundedDocuments.setDocuments(documents.getDocuments());
           documentsRepository.save(foundedDocuments);
           return foundedDocuments;
       }else throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }
}

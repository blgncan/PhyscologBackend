package com.physcolog.controllers;

import com.physcolog.entities.Documents;
import com.physcolog.services.DocumentsServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentsController {
    private final DocumentsServices documentsServices;

    @PostMapping
    public ResponseEntity<Documents> saveDocuments(@RequestBody Documents documents){
      Documents savedDocuments=  documentsServices.saveDocuments(documents);
      return ResponseEntity.ok(savedDocuments);
    }
    @GetMapping("/all")
    public List<Documents> getAllDocuments(){
        return documentsServices.getAllDocuments();
    }
    @GetMapping("/{documentsId}")
    public Optional<Documents> getDocumentsBtId(@PathVariable Long documentsId){
        return documentsServices.getDocumentsBtId(documentsId);
    }
    @GetMapping("/latest-one")
    public ResponseEntity<Documents> getLatestOneDocuments(){
        return documentsServices.getLatestOneDocuments();
    }
    @DeleteMapping("/{documentsId}")
    public Documents deleteDocuments(@PathVariable Long documentsId){
        return documentsServices.deleteDocuments(documentsId);
    }
    @PutMapping("/{documentsId}")
    public ResponseEntity<Documents> updateDocuments(@PathVariable Long documentsId,@RequestBody Documents documents){
      Documents updatedDocuments=  documentsServices.updateDocuments(documentsId,documents);
      return ResponseEntity.ok(updatedDocuments);
    }
}

package com.physcolog.services;

import com.physcolog.entities.About;
import com.physcolog.helper.FileTypeValidator;
import com.physcolog.helper.ImageResizer;
import com.physcolog.repository.AboutRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Data
@RequiredArgsConstructor
public class AboutService {
    @Value("${upload.path}") // application.properties'den alacağımız upload klasörü
    private String uploadPath;
    private final AboutRepository aboutRepository;


    public About getLatestAboutById(){
          // burası en son kaydı çeken logic.
       return aboutRepository.findFirstByOrderByIdDesc()
               .orElseThrow(() -> new RuntimeException("Henüz hakkımızda ile ilgili bir kayıt yok.Lütfen önce kaydedin..!"));
    }

    public List<About> getAllAbout() {
        // Tüm kayıtları "id" alanına göre azalan sırada çekiyoruz.
        return aboutRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public About saveNewAbout(String namesurname, String jobtitle, MultipartFile photo, String details) throws IOException {

        // Upload dizini kontrol et
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            try {
                Files.createDirectories(uploadDir); // Klasör yoksa oluştur
            } catch (IOException e) {
                throw new RuntimeException("Upload klasörü oluşturulamadı", e);
            }
        }
        // Fotoğraf yükleme ve boyutlandırma işlemi
        String fileName = null;
        if (photo != null && !photo.isEmpty()) {
            // Format kontrolü
            if (!FileTypeValidator.isImageFile(photo)) {
                throw new IllegalArgumentException("Yüklenen dosya geçerli bir resim formatında değil!");
            }

            fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            Path tempFilePath = Paths.get(uploadPath, "temp_" + fileName);
            Files.write(tempFilePath, photo.getBytes());

            // Resim boyutlandırma
            File resizedFile = ImageResizer.resizeImage(
                    Files.newInputStream(tempFilePath),
                    uploadPath + "/" + fileName,
                    310, 450
            );

            // Geçici dosyayı temizle
            Files.delete(tempFilePath);
        }

        // Veritabanına kaydetme
        About about = new About();
        about.setNamesurname(namesurname);
        about.setJobtitle(jobtitle);
        about.setPhoto(fileName != null ? "/images/" + fileName : null);
        about.setDetails(details);

        return aboutRepository.save(about);
    }

    public About updateAboutById(
            Long aboutId,
            String namesurname,
            String jobtitle,
            MultipartFile photo,
            String details) throws IOException {
        Optional<About> about=aboutRepository.findById(aboutId);
        //O id ye ait datanın olmama ihtimaline karşı optional yapıyoruz ve aşağıdaki if ile onu kontrol ediyoruz.
        if(about.isPresent()){//bulunduysa(isPresent)

            //fotoğraf işlemleri

            // Upload dizini kontrol et
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                try {
                    Files.createDirectories(uploadDir); // Klasör yoksa oluştur
                } catch (IOException e) {
                    throw new RuntimeException("Upload klasörü oluşturulamadı", e);
                }
            }
            // Fotoğraf yükleme ve boyutlandırma işlemi
            String fileName = null;
            if (photo != null && !photo.isEmpty()) {
                // Format kontrolü
                if (!FileTypeValidator.isImageFile(photo)) {
                    throw new IllegalArgumentException("Yüklenen dosya geçerli bir resim formatında değil!");
                }

                fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
                Path tempFilePath = Paths.get(uploadPath, "temp_" + fileName);
                Files.write(tempFilePath, photo.getBytes());

                // Resim boyutlandırma
                File resizedFile = ImageResizer.resizeImage(
                        Files.newInputStream(tempFilePath),
                        uploadPath + "/" + fileName,
                        310, 450
                );

                // Geçici dosyayı temizle
                Files.delete(tempFilePath);
            }


            //Bu ihtimal bulduğu ihtimaldir ve bunulan about nesnesini alıyoruz.
            About foundAbout=about.get();
            //Şimdi güncellemeyi yapıyoruz.
            foundAbout.setNamesurname(namesurname);
            foundAbout.setJobtitle(jobtitle);
            foundAbout.setPhoto(fileName != null ? "/images/" + fileName : null);
            foundAbout.setDetails(details);
            //Güncellediğimiz kaydı döndürüyoruz.
            aboutRepository.save(foundAbout);
            return foundAbout;
        }else throw new RuntimeException("Böyle bir Hakkımızda kaydı bulunamadı..!");
    }

    public About deleteById(Long aboutId) {
        Optional<About> about=aboutRepository.findById(aboutId);
        // id ye göre kaydı aradık.
        if(about.isPresent()){// kayıt varsa About nesnesine aldık kaydı
            About deletedAbout=about.get();
            aboutRepository.deleteById(aboutId);
            //Silinen kayıt frontend de gerekli olabilir diye onu döndürdük.
            return  deletedAbout;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");

    }


    public Optional<About> getAboutById(Long aboutId) {
       Optional<About> foundAbout= aboutRepository.findById(aboutId);
       if(foundAbout.isPresent()){
           return foundAbout;
       }else throw new RuntimeException("Kayıt Bulunamadı");
    }


}

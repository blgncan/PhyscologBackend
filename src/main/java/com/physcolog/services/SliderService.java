package com.physcolog.services;

import com.physcolog.entities.About;
import com.physcolog.entities.Slider;
import com.physcolog.helper.FileTypeValidator;
import com.physcolog.helper.ImageResizer;
import com.physcolog.repository.SliderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
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
public class SliderService {
@Value("${upload.path}") // application.properties'den alacağımız upload klasörü
private String uploadPath;
private final SliderRepository sliderRepository;
    //Tüm Sliderları id ye göre tersten sıralayıp çekiyoruz.
    public List<Slider> getAllSliderShorByDesc() {
        return sliderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    //Yeni Slider kaydediyoruz.
    public Slider createNewSlider(String title, String description, MultipartFile photo) throws IOException
    {
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
                    1920, 1088
            );

            // Geçici dosyayı temizle
            Files.delete(tempFilePath);
        }
        // Veritabanına kaydetme
        Slider slider = new Slider();
        slider.setTitle(title);
        slider.setDescription(description);
        slider.setPhoto(fileName != null ? "/images/" + fileName : null);
       return sliderRepository.save(slider);
    }

    public Slider updateSliderById(Long sliderId, String title, String description, MultipartFile photo) throws IOException {
        Optional<Slider> slider=sliderRepository.findById(sliderId);
        //O id ye ait datanın olmama ihtimaline karşı optional yapıyoruz ve aşağıdaki if ile onu kontrol ediyoruz.
        if(slider.isPresent()){//bulunduysa(isPresent)

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
                        1920, 1088
                );

                // Geçici dosyayı temizle
                Files.delete(tempFilePath);
            }


            //Bu ihtimal bulduğu ihtimaldir ve bunulan slider nesnesini alıyoruz.
            Slider foundSlider=slider.get();
            //Şimdi güncellemeyi yapıyoruz.
            foundSlider.setTitle(title);
            foundSlider.setDescription(description);
            foundSlider.setPhoto(fileName != null ? "/images/" + fileName : null);

            //Güncellediğimiz kaydı döndürüyoruz.
            sliderRepository.save(foundSlider);
            return foundSlider;
        }else throw new RuntimeException("Böyle bir Hakkımızda kaydı bulunamadı..!");
    }

    public Optional<Slider> getSliderById(Long sliderId) {
        Optional<Slider> foundSlider= sliderRepository.findById(sliderId);
        if(foundSlider.isPresent()){
            return foundSlider;
        }else throw new RuntimeException("Kayıt Bulunamadı");
    }

    public Slider deleteById(Long sliderId) {

        Optional<Slider> slider=sliderRepository.findById(sliderId);
        // id ye göre kaydı aradık.
        if(slider.isPresent()){// kayıt varsa Slider nesnesine aldık kaydı
            Slider deletedSlider=slider.get();
            sliderRepository.deleteById(sliderId);
            //Silinen kayıt frontend de gerekli olabilir diye onu döndürdük.
            return  deletedSlider;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }
}

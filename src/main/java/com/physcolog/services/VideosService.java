package com.physcolog.services;

import com.physcolog.entities.Videos;
import com.physcolog.helper.ValidationException;
import com.physcolog.repository.VideosRepository;
import jakarta.annotation.PostConstruct;
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
public class VideosService {
    private final VideosRepository videosRepository;

    public Videos saveVideo(Videos videos) {
        //Boş alan kontrolü yapıyoruz.
        if (!StringUtils.hasText(videos.getTitle())){
            throw  new ValidationException("Lütfen video başlık bölümünü boş geçmeyiniz..!");
        }
        if(!StringUtils.hasText(videos.getCategory())){
            throw new ValidationException("Lütfen kategori bölümünü boş geçmeyiniz");
        }
        if (!StringUtils.hasText(videos.getEmbed())){
            throw new ValidationException("Lütfen embed kodunu boş geçmeyiniz");
        }

        return videosRepository.save(videos);
    }
    public Videos updateVideo(long videoId, Videos videos) {
       Optional<Videos> video=videosRepository.findById(videoId);
       if (video.isPresent()){
           Videos foundedVideo=video.get();
           foundedVideo.setTitle(videos.getTitle());
           foundedVideo.setCategory(videos.getCategory());
           foundedVideo.setEmbed(videos.getEmbed());

           //Boş alan kontrolü yapıyoruz.
           if (!StringUtils.hasText(videos.getTitle())){
               throw  new ValidationException("Lütfen video başlık bölümünü boş geçmeyiniz..!");
           }
           if(!StringUtils.hasText(videos.getCategory())){
               throw new ValidationException("Lütfen kategori bölümünü boş geçmeyiniz");
           }
           if (!StringUtils.hasText(videos.getEmbed())){
               throw new ValidationException("Lütfen embed kodunu boş geçmeyiniz");
           }

           videosRepository.save(foundedVideo);
           return foundedVideo;
       }throw new RuntimeException("Böyle bir kayıt bulunamadı..!");
    }
    public List<Videos> getAllVideos() {
        return videosRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
    public Optional<Videos> getVideoById(long videoId) {
        Optional<Videos> foundedVideo=videosRepository.findById(videoId);
        if (foundedVideo.isPresent()){
            return foundedVideo;
        }else throw new RuntimeException("Kayıt Bulunamadı");
    }
    public List<Videos> getLatestFourVideos() {
        return videosRepository.getLatestFourVideos();
    }
    public Videos deleteVideoById(long videoId) {
        Optional<Videos> video=videosRepository.findById(videoId);
        if (video.isPresent())
        {
            Videos deletedVideo=video.get();
            videosRepository.deleteById(videoId);
            return deletedVideo;
        }else throw new RuntimeException("Belirtilen kayıt bulunamadı..!");
    }

    public List<Videos> getVideosByCategory(String videoCategory) {
        return videosRepository.findByCategory(videoCategory); // Dinamik olmalı!
    }

}

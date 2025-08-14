package com.physcolog.controllers;

import com.physcolog.entities.Videos;
import com.physcolog.services.VideosService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/videos")
public class VideosController {
    private final VideosService videosService;

    @PostMapping
    public ResponseEntity<Videos> saveVideo(@RequestBody Videos videos){
        Videos savedVideo=videosService.saveVideo(videos);
        return ResponseEntity.ok(savedVideo);
    }
    @PutMapping("/{videoId}")
    public ResponseEntity<Videos> updateVideo(@PathVariable long videoId,@RequestBody Videos videos){
        Videos updatedVideo=videosService.updateVideo(videoId,videos);
        return ResponseEntity.ok(updatedVideo);
    }
    @GetMapping
    public List<Videos> getAllVideos(){
        return videosService.getAllVideos();
    }
    @GetMapping("/{videoId}")
    public Optional<Videos> getVideoById(@PathVariable long videoId){
        return videosService.getVideoById(videoId);
    }
    @GetMapping("/latest-four")
    public List<Videos> getLatestFourVideos(){
        return videosService.getLatestFourVideos();
    }

    @GetMapping("/category/{videoCategory}")
    public List<Videos> getVideosByCategory(@PathVariable String videoCategory) {
        return videosService.getVideosByCategory(videoCategory);
    }
    @DeleteMapping("/{videoId}")
    public Videos deleteVideoById(@PathVariable long videoId){
        return videosService.deleteVideoById(videoId);
    }
}

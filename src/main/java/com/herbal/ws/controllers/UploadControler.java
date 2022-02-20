package com.herbal.ws.controllers;


import com.herbal.ws.entities.ImageEntity;
import com.herbal.ws.response.ImageResponse;
import com.herbal.ws.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/upload") // localhost:8084/api/upload
@RequiredArgsConstructor
public class UploadControler {
    private final ImageService imageService;
    @GetMapping
    public ResponseEntity<List<ImageEntity>> getTodos() {
        List<ImageEntity> imageEntityList = imageService.getAllImages();
        return new ResponseEntity<List<ImageEntity>>(imageEntityList, HttpStatus.OK);
    }

    @PostMapping(
            path = "/add_image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImageEntity> saveTodo(
                                         @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(imageService.store(file), HttpStatus.OK);
    }

    @GetMapping(value = "/image/download/{id}")
    public byte[] downloadTodoImage(@PathVariable("id") String idClientPhoto) {
        return imageService.downloadImage(idClientPhoto);
    }
}
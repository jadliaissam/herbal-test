package com.herbal.ws.services;

import com.herbal.ws.entities.ImageEntity;
import com.herbal.ws.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    ImageEntity store( MultipartFile file) throws IOException;
    byte[] downloadImage(String idClientPhoto);
    List<ImageEntity> getAllImages();
}

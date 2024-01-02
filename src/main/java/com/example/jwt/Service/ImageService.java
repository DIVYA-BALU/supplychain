package com.example.jwt.Service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import com.example.jwt.Entity.Image;


public interface ImageService {
     public String storeImage(String id,MultipartFile file) throws IOException;
    public Image saveData(Image img);
    public Image getById(String id);
    public byte[] downloadImage(Image img);
    public Boolean deleteImage(Image img);
}

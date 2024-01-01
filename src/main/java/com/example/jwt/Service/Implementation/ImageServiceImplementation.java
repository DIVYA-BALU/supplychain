package com.example.jwt.Service.Implementation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.jwt.Service.ImageService;
import com.example.jwt.repository.ImageRepository;

@Service
public class ImageServiceImplementation implements ImageService{
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    ImageRepository imageRepository;
    
    public String storeImage(MultipartFile file){
        String fileName = file.getOriginalFilename();
        List<String> IMAGE_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");
        try {
        if (IMAGE_CONTENT_TYPES.contains(file.getContentType())==false) {
            return "Only image files are supported.";
        }
            gridFsTemplate.store(file.getInputStream(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public GridFsResource getImage(String fileName) {
        return gridFsTemplate.getResource(fileName);
    }
    
}

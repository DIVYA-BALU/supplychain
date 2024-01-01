package com.example.jwt.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {
     public String storeImage(MultipartFile file) throws IOException;
     public GridFsResource getImage(String fileName);
}

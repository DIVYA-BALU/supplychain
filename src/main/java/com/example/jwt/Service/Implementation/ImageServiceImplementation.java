package com.example.jwt.Service.Implementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.jwt.Entity.Image;
import com.example.jwt.Service.ImageService;
import com.example.jwt.repository.ImageRepository;

@Service
public class ImageServiceImplementation implements ImageService{

    @Autowired
    ImageRepository imageRepository;

    public Image saveData(Image img) {
        Image i = new Image();
        try {
          i = imageRepository.save(img);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return i;
      }

    public Image getById(String id) {
        try {
          Optional<Image> result = imageRepository.findById(id);
          return result.get();
        } catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }
    
    public String storeImage(String id,MultipartFile file){
        String fileName = file.getOriginalFilename();
        Image img = getById(id);
        System.out.println(img);
        List<String> IMAGE_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif");
        try {
        if (IMAGE_CONTENT_TYPES.contains(file.getContentType())==false) {
            return "Only image files are supported.";
        }
        String filePath = "/home/divya/Downloads/jwt/src/main/resources/images/" + fileName;
        img.setImagedata(filePath);
        file.transferTo(new File(filePath));
        imageRepository.save(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public byte[] downloadImage(Image img) {
    String filePath = img.getImagedata();
    try {
      if (filePath == null)
        return null;
      byte[] images = Files.readAllBytes(new File(filePath).toPath());
      return images;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public Image updateData(Image img) {
    try {

      return imageRepository.save(img);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Boolean deleteImage(Image img) {
    File file = new File(img.getImagedata());
    file.delete();
    img.setImagedata(null);
    updateData(img);
    return true;
  }

}

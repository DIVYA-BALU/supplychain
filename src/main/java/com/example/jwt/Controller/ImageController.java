package com.example.jwt.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.example.jwt.Entity.Image;
import com.example.jwt.Service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/save")
    public ResponseEntity<Image> insertFacility(@RequestBody Image img) {
        try {
            
            return new ResponseEntity<Image>( imageService.saveData(img),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Image>( new Image(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/select/{id}")
    public ResponseEntity<Image> selectById(@PathVariable("id")String id){
        try {
            if(imageService.getById(id)==null)
                return new ResponseEntity<>(new Image(),HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Image>( imageService.getById(id),HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( new Image(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/upload/{id}")
    public ResponseEntity<String> uploadImage(@PathVariable String id,@RequestParam("file") MultipartFile file) throws IOException {
        
        String fileName = imageService.storeImage(id,file);
        return ResponseEntity.ok("Image uploaded: " + fileName);
    }

    @GetMapping("downloadImage/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) {
        try {
            Image img=imageService.getById(id);
            if(!img.equals(new Image())){            
                return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/jpeg"))
				.body(imageService.downloadImage(img));
            }
            else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("deleteImage/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable String id) {
        try {
            Image img=imageService.getById(id);
            if(img!= new Image())
            return new ResponseEntity<>(imageService.deleteImage(img), HttpStatus.OK);
            else
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
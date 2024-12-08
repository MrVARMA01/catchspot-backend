package com.project.catchspot.controller;
import com.project.catchspot.entity.Collection;
import com.project.catchspot.entity.File;
import com.project.catchspot.service.CollectionService;
import com.project.catchspot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/all-files")
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> files = fileService.getAllFiles();
        if (files.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(files);
        }
    }
    @GetMapping("/all-posts")
    public ResponseEntity<List<File>> getAllPosts() {
        List<File> files = fileService.getAllPosts();
        if (files.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(files);
        }
    }
    @GetMapping("/all-connects-posts/{userId}")
    public ResponseEntity<List<File>> getConnectsPosts(@PathVariable long userId) {
        List<File> files = fileService.getConnectsPosts(userId);
        if (files.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(files);
        }
    }
    @GetMapping("/file/{id}")
    public ResponseEntity<?> getPosts(@PathVariable long id) {
        if (id == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(fileService.getFile(id));
        }
    }
}

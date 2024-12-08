package com.project.catchspot.controller;
import com.project.catchspot.entity.Collection;
import com.project.catchspot.entity.Privacy;
import com.project.catchspot.entity.User;
import com.project.catchspot.service.CollectionService;
import com.project.catchspot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class CollectionController {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private UserService userService;

    @PostMapping("/create-collection")
    public ResponseEntity<?> createCollection(
            @RequestParam long userId,
            @RequestParam String collectionName,
            @RequestParam List<MultipartFile> files,
            @RequestParam Privacy privacy,
            @RequestParam boolean post) throws IOException {
        User user = userService.findByUserId(userId);
        if (user != null) {
            return ResponseEntity.ok(collectionService.createCollection(userId, collectionName, files,privacy,post));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/user-collections")
    public ResponseEntity<List<Collection>> getUserCollections(@RequestParam long userId) {
        List<Collection> collections = collectionService.findAllCollectionsByUser(userId);
        if (collections == null || collections.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(collections);
        }
    }

    @GetMapping("/collection")
    public ResponseEntity<Collection> getCollection(@RequestParam long collectionId) {
        Collection collection = collectionService.findById(collectionId);
        if (collection != null) {
            return new ResponseEntity<>(collection,HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/upload-to-collection")
    public ResponseEntity<Collection> uploadToCollection(
            @RequestParam List<MultipartFile> files,
            @RequestParam long collectionId,
            @RequestParam Privacy privacy,
            @RequestParam boolean post) throws IOException {
            return ResponseEntity.ok(collectionService.uploadToCollection(files,collectionId,privacy,post));
    }

    @PostMapping("/rename-collection")
    public ResponseEntity<Collection> renameCollection(@RequestParam long collectionId, @RequestParam String collectionName){
        return ResponseEntity.ok(collectionService.renameCollection(collectionId,collectionName));
    }

//    @DeleteMapping("/delete-collection")
//    public ResponseEntity<Collection> deleteCollection(@RequestParam long collectionId, @RequestParam String collectionName){
//        return ResponseEntity.ok(collectionService.renameCollection(collectionId,collectionName));
//    }

}

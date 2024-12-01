package com.project.catchspot.serviceImpl;
import com.project.catchspot.entity.Collection;
import com.project.catchspot.entity.File;
import com.project.catchspot.entity.User;
import com.project.catchspot.repository.CollectionRepository;
import com.project.catchspot.repository.FileRepository;
import com.project.catchspot.repository.UserRepository;
import com.project.catchspot.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;

    private final String UPLOAD_DIR = "uploads/";


    @Override
    public Collection createCollection(long userId, String collectionName, List<MultipartFile> files) throws IOException {
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            Collection collection = new Collection();
            collection.setName(collectionName);
            collection.setUserId(user.getUserId());
            collection = collectionRepository.save(collection);
            List<Collection> userCollectionList = user.getCollections();
            userCollectionList.add(collection);
            userRepository.save(user);
            Collection uploadedCollection = uploadToCollection(files, collection.getId());
            return uploadedCollection;
        } else {
            return null;
        }
    }


    @Override
    public Collection uploadToCollection(List<MultipartFile> files, long collectionId) throws IOException {
        // Create directory if it doesn't exist
        java.io.File uploadDir = new java.io.File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Collection collection = collectionRepository.findById(collectionId);
        if (collection == null) {
            throw new RuntimeException("Collection not found");
        }
        List<File> imagesList = collection.getFiles();

        // Loop through each file
        for (MultipartFile file : files) {
            // Generate a unique filename
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // Save the file to the file system
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Create and save the Image entity
            File image = new File();
            image.setFileUrl("/uploads/" + fileName);  // Set the relative file path as the image URL
            image.setCollectionId(collection.getId());
            image.setUserId(collection.getUserId());

            imagesList.add(image);  // Add the image to the collection's image list
        }

        // Update the collection with the new images
        collection.setFiles(imagesList);
        collectionRepository.save(collection);  // Save the updated collection

        return collection;
    }

    @Override
    public List<Collection> findAllCollectionsByUser(long userId) {
        User user = userRepository.findByUserId(userId);
        System.out.println(user.getCollections());
        return user.getCollections();
//        return collectionRepository.findAll().stream().filter(collection -> collection.getUserId() == (user.getUserId())).collect(Collectors.toList());
    }

    @Override
    public Collection findById(long collectionId) {
        return collectionRepository.findById(collectionId);
    }

    @Override
    public Collection renameCollection(long collectionId, String collectionName) {
        Collection collection = collectionRepository.findById(collectionId);
        collection.setName(collectionName);
        return collectionRepository.save(collection);
    }

}

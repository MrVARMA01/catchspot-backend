package com.project.catchspot.service;
import com.project.catchspot.entity.Collection;
import com.project.catchspot.entity.FileAccess;
import com.project.catchspot.entity.Privacy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CollectionService {

    Collection createCollection(long userId, String collectionName, List<MultipartFile> files, Privacy privacy, boolean post) throws IOException ;
    List<Collection> findAllCollectionsByUser(long userId);
    Collection findById(long collectionId);
    Collection uploadToCollection(List<MultipartFile> files, long collectionId, Privacy privacy, boolean post) throws IOException;
    Collection renameCollection(long collectionId, String collectionName);
}

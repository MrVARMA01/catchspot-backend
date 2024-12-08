package com.project.catchspot.serviceImpl;
import com.project.catchspot.DTO.UserDto;
import com.project.catchspot.entity.Collection;
import com.project.catchspot.entity.File;
import com.project.catchspot.entity.User;
import com.project.catchspot.repository.FileRepository;
import com.project.catchspot.service.CollectionService;
import com.project.catchspot.service.FileService;
import com.project.catchspot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private UserService userService;

    private final String UPLOAD_DIR = "uploads/";

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public List<File> getAllPosts() {
        return fileRepository.findAllPosts();
    }

    @Override
    public List<File> getConnectsPosts(long userId) {
        List<Long> userConnectsIds = userService.findByUserId(userId).getConnects();
        List<File> allPosts = getAllPosts();
        List<File> connectsPosts = new ArrayList<>();
        for(File file:allPosts){
            if (userConnectsIds.contains(file.getUserId())){
                connectsPosts.add(file);
            }
        }
        return connectsPosts;
    }

    @Override
    public File getFile(long fileId) {
        return fileRepository.findById(fileId);
    }


}

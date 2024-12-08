package com.project.catchspot.service;

import com.project.catchspot.entity.Collection;
import com.project.catchspot.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<File> getAllFiles();
    List<File> getAllPosts();
    List<File> getConnectsPosts(long userId);
    File getFile(long fileId);
}

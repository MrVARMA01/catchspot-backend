package com.project.catchspot.serviceImpl;

import com.project.catchspot.entity.File;
import com.project.catchspot.entity.FileAccess;
import com.project.catchspot.repository.FileAccessRepository;
import com.project.catchspot.repository.FileRepository;
import com.project.catchspot.service.FileAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileAccessServiceImpl implements FileAccessService {
    @Autowired
    private FileAccessRepository fileAccessRepository;
    @Autowired
    private FileRepository fileRepository;

    @Override
    public File save(FileAccess access) {
        File file = fileRepository.findById(access.getFileId());
        List<FileAccess> accessedList = file.getAccessedToUsers();
        accessedList.add(access);
        file.setAccessedToUsers(accessedList);
        return fileRepository.save(file);
    }

}

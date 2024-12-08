package com.project.catchspot.controller;
import com.project.catchspot.entity.FileAccess;
import com.project.catchspot.service.FileAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileAccessController {
    @Autowired
    private FileAccessService fileAccessService;

    @PostMapping("/file-access")
    public ResponseEntity<?> saveAccess(@RequestBody FileAccess fileAccess){
        return new ResponseEntity<>(fileAccessService.save(fileAccess), HttpStatus.OK);
    }
}

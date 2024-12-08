package com.project.catchspot.repository;

import com.project.catchspot.entity.FileAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAccessRepository extends JpaRepository<FileAccess,Long> {
    FileAccess findById(long id);
}

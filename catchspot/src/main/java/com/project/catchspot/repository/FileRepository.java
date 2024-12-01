package com.project.catchspot.repository;
import com.project.catchspot.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    File findById(long id);
}

package com.project.catchspot.repository;
import com.project.catchspot.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    File findById(long id);
    @Query("SELECT f FROM File f WHERE f.post = true")
    List<File> findAllPosts();
}

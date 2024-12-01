package com.project.catchspot.repository;
import com.project.catchspot.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection,Long> {
    Collection findById(long collectionId);
}

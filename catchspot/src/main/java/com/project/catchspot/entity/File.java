package com.project.catchspot.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fileUrl;  // URL or file path of the stored image
    private long collectionId;
    private boolean isPublic;
    private boolean visibleOnlyForMe;
    private List<Long> selectedAccess = new ArrayList<>();
    private boolean post;
    private long userId;
}

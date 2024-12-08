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
    private Privacy privacy;
    private boolean post;
    private boolean rating;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FileAccess> accessedToUsers = new ArrayList<>();
    private long userId;
}

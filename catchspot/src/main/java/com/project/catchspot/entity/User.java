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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private File profilePic;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private long phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Collection> collections = new ArrayList<>();

    private boolean isPrivateAccount;

    private List<Long> connects = new ArrayList<>();

    private List<Long> gotConnectRequests = new ArrayList<>();

    private List<Long> sentConnectRequests = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;

    // Methods to manage connections
    public void addSentConnectRequest(Long connectId) {
        if (!sentConnectRequests.contains(connectId)) {
            sentConnectRequests.add(connectId);
        }
    }

    public void addGotConnectRequest(Long requesterId) {
        if (!gotConnectRequests.contains(requesterId)) {
            gotConnectRequests.add(requesterId);
        }
    }

    public void addConnection(Long connectUserId) {
        if (!connects.contains(connectUserId)) {
            connects.add(connectUserId);
        }
    }

    public void removeConnection(Long connectUserId) {
        connects.remove(connectUserId);
    }
}

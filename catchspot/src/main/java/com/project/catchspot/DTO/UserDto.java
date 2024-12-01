package com.project.catchspot.DTO;
import com.project.catchspot.entity.Collection;
import com.project.catchspot.entity.File;
import com.project.catchspot.entity.Gender;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {
    private long userId;
    private File profilePic;
    private String email;
    private String firstName;
    private String lastName;
    private long phone;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Collection> collections = new ArrayList<>();
    private boolean isPrivateAccount;
    private List<Long> connects = new ArrayList<>();
    private Gender gender;
}

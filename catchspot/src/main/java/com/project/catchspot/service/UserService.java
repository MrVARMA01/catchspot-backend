package com.project.catchspot.service;
import com.project.catchspot.DTO.UserDto;
import com.project.catchspot.JWT.JwtRequest;
import com.project.catchspot.JWT.JwtResponse;
import com.project.catchspot.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    // --------------------------------- REGISTER  --------------------------------- //
    void register(User register);
    JwtResponse login(JwtRequest request) throws NullPointerException ;
    // --------------------------------- USER DATA  --------------------------------- //
    User findByEmail(String username);
    User findByUserId(long userId);
    User setProfilePic(long imageId,long userId);
    List<User> getAllUsers();
    // --------------------------------- ALL ABOUT CONNECTS  --------------------------------- //
    List<User> getAllConnects(long userId);
    List<UserDto> getUserConnects(long userId);
    String sendConnectRequest(long userId, long connectId);
    String acceptConnectRequest(long userId, long connectId);
    List<UserDto> getUserAllGotConnectRequests(long userId);
    List<UserDto> getUserAllSentConnectRequests(long userId);
}

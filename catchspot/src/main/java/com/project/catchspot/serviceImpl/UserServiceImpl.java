package com.project.catchspot.serviceImpl;
import com.project.catchspot.DTO.UserDto;
import com.project.catchspot.JWT.JwtHelper;
import com.project.catchspot.JWT.JwtRequest;
import com.project.catchspot.JWT.JwtResponse;
import com.project.catchspot.entity.File;
import com.project.catchspot.entity.Status;
import com.project.catchspot.entity.User;
import com.project.catchspot.repository.FileRepository;
import com.project.catchspot.repository.UserRepository;
import com.project.catchspot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private JwtHelper jwtHelper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);




    // --------------------------------- LOGIN & REGISTER  --------------------------------- //

    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
        System.out.println("Data saved!");
    }

    @Override
    public JwtResponse login(JwtRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        // Check if the user exists and the password matches
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // Generate JWT token
            JwtResponse response = new JwtResponse();
            response.setJwtToken(jwtHelper.generateToken(request.getEmail()));
            response.setUserId(user.getUserId());
            return response;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }




    // --------------------------------- USER DATA  --------------------------------- //

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User setProfilePic(long imageId,long userId) {
        User user = userRepository.findByUserId(userId);
        File profilePic = fileRepository.findById(imageId);
        user.setProfilePic(profilePic);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }




    // --------------------------------- ALL ABOUT CONNECTS  --------------------------------- //

    @Override
    public List<User> getAllConnects(long userId) {
        List<User> userList = userRepository.findAll();
        userList.removeIf(user -> user.getUserId() == userId);
        return userList;
    }

    @Override
    public List<UserDto> getUserConnects(long userId) {
        User user = userRepository.findByUserId(userId);
        List<Long> connectsIds = user.getConnects();
        List<UserDto> connectsList = new ArrayList<>();
        for(long connectID:connectsIds){
            UserDto userDto = new UserDto();
            User connect = userRepository.findByUserId(connectID);
            userDto.setUserId(connect.getUserId());
            userDto.setProfilePic(connect.getProfilePic());
            userDto.setFirstName(connect.getFirstName());
            userDto.setLastName(connect.getLastName());
            userDto.setEmail(connect.getEmail());
            userDto.setPhone(connect.getPhone());
            userDto.setCollections(connect.getCollections());
            userDto.setPrivateAccount(connect.isPrivateAccount());
            userDto.setConnects(connect.getConnects());
            userDto.setGender(connect.getGender());
            connectsList.add(userDto);
            System.out.println(connectsList);
        }
        return connectsList;
    }

    @Override
    public List<UserDto> getUserAllGotConnectRequests(long userId) {
        User user = userRepository.findByUserId(userId);
        List<Long> connectsIds = user.getGotConnectRequests();
        List<UserDto> connectsList = new ArrayList<>();
        for(long connectID:connectsIds){
            UserDto userDto = new UserDto();
            User connect = userRepository.findByUserId(connectID);
            userDto.setUserId(connect.getUserId());
            userDto.setProfilePic(connect.getProfilePic());
            userDto.setFirstName(connect.getFirstName());
            userDto.setLastName(connect.getLastName());
            userDto.setEmail(connect.getEmail());
            userDto.setPhone(connect.getPhone());
            userDto.setCollections(connect.getCollections());
            userDto.setPrivateAccount(connect.isPrivateAccount());
            userDto.setConnects(connect.getConnects());
            userDto.setGender(connect.getGender());
            connectsList.add(userDto);
            System.out.println(connectsList);
        }
        return connectsList;
    }

    @Override
    public List<UserDto> getUserAllSentConnectRequests(long userId) {
        User user = userRepository.findByUserId(userId);
        List<Long> connectsIds = user.getSentConnectRequests();
        List<UserDto> connectsList = new ArrayList<>();
        for(long connectID:connectsIds){
            UserDto userDto = new UserDto();
            User connect = userRepository.findByUserId(connectID);
            userDto.setUserId(connect.getUserId());
            userDto.setProfilePic(connect.getProfilePic());
            userDto.setFirstName(connect.getFirstName());
            userDto.setLastName(connect.getLastName());
            userDto.setEmail(connect.getEmail());
            userDto.setPhone(connect.getPhone());
            userDto.setCollections(connect.getCollections());
            userDto.setPrivateAccount(connect.isPrivateAccount());
            userDto.setConnects(connect.getConnects());
            userDto.setGender(connect.getGender());
            connectsList.add(userDto);
            System.out.println(connectsList);
        }
        return connectsList;
    }

    @Override
    public String sendConnectRequest(long userId, long connectId) {
        User user1 = userRepository.findByUserId(userId);
        User user2 = userRepository.findByUserId(connectId);
        // Initialize lists if they are null
        if (user1.getGotConnectRequests() == null) user1.setGotConnectRequests(new ArrayList<>());
        if (user1.getSentConnectRequests() == null) user1.setSentConnectRequests(new ArrayList<>());
        if (user2.getGotConnectRequests() == null) user2.setGotConnectRequests(new ArrayList<>());
        if (user2.getSentConnectRequests() == null) user2.setSentConnectRequests(new ArrayList<>());

        if (!user1.getGotConnectRequests().contains(connectId) && !user1.getSentConnectRequests().contains(connectId))
        {
            user1.addSentConnectRequest(connectId);
            userRepository.save(user1);// Save the updated user
        }
        if (!user2.getGotConnectRequests().contains(userId) && !user2.getSentConnectRequests().contains(userId)) {
            user2.addGotConnectRequest(userId);
            userRepository.save(user2); // Save the updated user
        }
        return "Request Sent";
    }

    @Override
    public String acceptConnectRequest(long userId, long requesterID) {
        User user1 = userRepository.findByUserId(userId);
        User user2 = userRepository.findByUserId(requesterID);
        if (!user1.getConnects().contains(requesterID)) {
            user1.addConnection(requesterID);
            List<Long> allGotRequests1 = user1.getGotConnectRequests();
            List<Long> allSentRequests1 = user1.getSentConnectRequests();
            allGotRequests1.remove(user2.getUserId());
            allSentRequests1.remove(user2.getUserId());
            user1.setGotConnectRequests(allGotRequests1);
            user1.setSentConnectRequests(allSentRequests1);
            userRepository.save(user1); // Save the updated user
        }
        if (!user2.getConnects().contains(userId)) {
            user2.addConnection(userId);
            List<Long> allGotRequests2 = user2.getGotConnectRequests();
            List<Long> allSentRequests2 = user2.getSentConnectRequests();
            allGotRequests2.remove(user1.getUserId());
            allSentRequests2.remove(user1.getUserId());
            user2.setGotConnectRequests(allGotRequests2);
            user2.setSentConnectRequests(allSentRequests2);
            userRepository.save(user2); // Save the updated user
        }
        return "connected with user";
    }

}

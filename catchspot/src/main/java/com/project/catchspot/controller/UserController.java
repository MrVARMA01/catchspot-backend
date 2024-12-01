package com.project.catchspot.controller;
import com.project.catchspot.entity.User;
import com.project.catchspot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;


    // --------------------------------- REGISTER  --------------------------------- //

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        service.register(user); // assuming the service performs the action and returns void
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }



    // --------------------------------- FIND USERS AND USER DATA  --------------------------------- //

    @GetMapping("/all-users")
    public ResponseEntity<?> allUsers(){
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user-by-email")
    public ResponseEntity<?> userByEmail(@RequestParam String email){
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> userData(@PathVariable long id){
        return new ResponseEntity<>(service.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/user-profile-pic")
    public ResponseEntity<?> userProfilePic(@RequestParam long imageId, @RequestParam long userId){
        return new ResponseEntity<>(service.setProfilePic(imageId,userId), HttpStatus.OK);
    }




    // --------------------------------- ALL ABOUT CONNECTS  --------------------------------- //

    @GetMapping("/get-user-connects")
    public ResponseEntity<?> getUserConnects(@RequestParam long userId){
        return new ResponseEntity<>(service.getUserConnects(userId), HttpStatus.OK);
    }

    @GetMapping("/all-connects/{userId}")
    public ResponseEntity<?> getAllConnectsSuggestions(@PathVariable long userId){
        return new ResponseEntity<>(service.getAllConnects(userId), HttpStatus.OK);
    }

    @GetMapping("/get-user-all-got-connect-requests/{userId}")
    public ResponseEntity<?> getUserAllGotConnectRequests(@PathVariable long userId){
        return new ResponseEntity<>(service.getUserAllGotConnectRequests(userId), HttpStatus.OK);
    }

    @GetMapping("/get-user-all-sent-connect-requests/{userId}")
    public ResponseEntity<?> getUserAllSentConnectRequests(@PathVariable long userId){
        return new ResponseEntity<>(service.getUserAllSentConnectRequests(userId), HttpStatus.OK);
    }

    @PostMapping("/send-connect-request")
    public ResponseEntity<?> getConnectRequests(@RequestParam long userId, @RequestParam long connectId){
        service.sendConnectRequest(userId,connectId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request Sent !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/accept-connect-request")
    public ResponseEntity<?> acceptConnectRequest(@RequestParam long userId, @RequestParam long connectId){
        service.acceptConnectRequest(userId,connectId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Connected To User !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

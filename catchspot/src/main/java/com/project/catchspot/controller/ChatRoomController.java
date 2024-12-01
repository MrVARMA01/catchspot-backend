//package com.project.catchspot.controller;
//
//import com.project.catchspot.service.ChatRoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class ChatRoomController {
//
//    @Autowired
//    private ChatRoomService chatRoomService;
//
//    @GetMapping("/get-chat-room")
//    public ResponseEntity<?> getChatRoom(@RequestParam String sender, @RequestParam String receiver){
//        return new ResponseEntity<>(chatRoomService.getChatRoomId(sender,receiver), HttpStatus.OK);
//    }
//}


package com.project.catchspot.controller;

import com.project.catchspot.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("/chatroom/{user1}/{user2}")
    public ResponseEntity<?> getChatRoomId(@PathVariable long user1, @PathVariable long user2) {
        return new ResponseEntity<>(chatRoomService.getChatRoomId(user1, user2), HttpStatus.OK);
    }

    @PostMapping("/chatroom/create")
    public ResponseEntity<?> createChatRoom(@RequestParam long user1, @RequestParam long user2) {
        return new ResponseEntity<>( chatRoomService.createChatRoom(user1, user2), HttpStatus.OK);
    }
}

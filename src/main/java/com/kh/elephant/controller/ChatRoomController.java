package com.kh.elephant.controller;

import com.kh.elephant.domain.ChatRoom;
import com.kh.elephant.repo.ChatRoomDAO;
import com.kh.elephant.repo.UserChatRoomInfoDAO;
import com.kh.elephant.service.ChatRoomService;
import com.kh.elephant.service.UserChatRoomInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qiri/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ChatRoomController {

    @Autowired
    private ChatRoomService crService;

    @Autowired
    private UserChatRoomInfoService ucriService;

    // 채팅 리스트 화면
    @GetMapping("/chat/rooms")
    public ResponseEntity<List<ChatRoom>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(crService.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/chat/room/{id}")
    public ResponseEntity<ChatRoom> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(crService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @PostMapping("/room")
//    @ResponseBody
//    public ChatRoom createRoom(@RequestBody String name) {
//        System.out.println(name);
//        return dao.createChatRoom(name);
//    }
//
//    @GetMapping("/chat/room/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "/chatRoom";
//    }
//
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoom roomInfo(@PathVariable String roomId)
//    {return dao.findRoomById(roomId);}
}

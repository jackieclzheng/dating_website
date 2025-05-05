package com.yuanfentiankon.controller;

import com.yuanfentiankon.model.entity.Message;
import com.yuanfentiankon.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(
            @RequestParam Long userId1,
            @RequestParam Long userId2) {
        return ResponseEntity.ok(messageService.findConversation(userId1, userId2));
    }

    @GetMapping("/unread")
    public ResponseEntity<List<Message>> getUnreadMessages(@RequestParam Long userId) {
        return ResponseEntity.ok(messageService.findUnreadMessages(userId));
    }

    @GetMapping("/unread/count")
    public ResponseEntity<Integer> getUnreadCount(@RequestParam Long userId) {
        return ResponseEntity.ok(messageService.countUnreadMessages(userId));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<Message>> getLatestMessages(@RequestParam Long userId) {
        return ResponseEntity.ok(messageService.findLatestMessages(userId));
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Object>> getContacts(@RequestParam Long userId) {
        return ResponseEntity.ok(messageService.findContactsByUserId(userId));
    }

    // 新增接口
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.sendMessage(message));
    }

    @PutMapping("/{messageId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long messageId) {
        messageService.markAsRead(messageId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Message>> getUserMessages(
            @PathVariable Long userId,
            Pageable pageable) {
        return ResponseEntity.ok(messageService.findMessagesByUserId(userId, pageable));
    }
} 

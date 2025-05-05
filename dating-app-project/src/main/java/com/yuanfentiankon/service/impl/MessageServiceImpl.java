package com.yuanfentiankon.service.impl;

import com.yuanfentiankon.model.entity.Message;
import com.yuanfentiankon.model.entity.User;
import com.yuanfentiankon.repository.MessageRepository;
import com.yuanfentiankon.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findConversation(Long userId1, Long userId2) {
        return messageRepository.findConversation(userId1, userId2);
    }

    @Override
    public List<Message> findUnreadMessages(Long userId) {
        return messageRepository.findUnreadMessages(userId);
    }

    @Override
    public Integer countUnreadMessages(Long userId) {
        return messageRepository.countUnreadMessages(userId);
    }

    @Override
    public List<Message> findLatestMessages(Long userId) {
        return messageRepository.findLatestMessages(userId);
    }

    @Override
    public Set<User> findContactsByUserId(Long userId) {
        return null;
    }

    @Override
    // public List<Object> findContactsByUserId(Long userId) {
    //     // 获取发送给我的联系人
    //     List<Object> senders = messageRepository.findSendersByReceiverId(userId);
    //     // 获取我发送给的联系人
    //     List<Object> receivers = messageRepository.findReceiversBySenderId(userId);
        
    //     // 使用 Set 去重
    //     Set<Object> contacts = new HashSet<>();
    //     contacts.addAll(senders);
    //     contacts.addAll(receivers);
        
    //     // 转换回 List 返回
    //     return new ArrayList<>(contacts);
    // }

//    @Override
    @Transactional
    public Message sendMessage(Message message) {
        // 设置消息为未读状态
        message.setRead(false);
        return messageRepository.save(message);
    }

    @Override
    public Message markAsRead(Long messageId) {
        return null;
    }

//    @Override
//    @Transactional
//    public void markAsRead(Long messageId) {
//        Message message = messageRepository.findById(messageId)
//                .orElseThrow(() -> new RuntimeException("消息不存在"));
//        message.setRead(true);
//        messageRepository.save(message);
//    }

    @Override
    @Transactional
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public Page<Message> findMessagesByUserId(Long userId, Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
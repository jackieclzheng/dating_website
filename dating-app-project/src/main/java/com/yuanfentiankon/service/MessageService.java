package com.yuanfentiankon.service;

import com.yuanfentiankon.model.entity.Message;
import com.yuanfentiankon.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface MessageService {
    /**
     * 查询两个用户之间的对话
     */
    List<Message> findConversation(Long userId1, Long userId2);

    /**
     * 查询用户的未读消息
     */
    List<Message> findUnreadMessages(Long userId);

    /**
     * 统计用户的未读消息数量
     */
    Integer countUnreadMessages(Long userId);

    /**
     * 查询用户的最新消息
     */
    List<Message> findLatestMessages(Long userId);

    /**
     * 查询用户的所有联系人（包括发送者和接收者）
     * @return 联系人集合
     */
    Set<User> findContactsByUserId(Long userId);

    /**
     * 发送消息
     */
    Message sendMessage(Message message);

    /**
     * 标记消息为已读
     */
    Message markAsRead(Long messageId);

    /**
     * 删除消息
     */
    void deleteMessage(Long messageId);

    /**
     * 分页查询用户的消息
     */
    Page<Message> findMessagesByUserId(Long userId, Pageable pageable);
}
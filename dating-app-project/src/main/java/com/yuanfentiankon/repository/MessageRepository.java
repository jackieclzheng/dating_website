package com.yuanfentiankon.repository;

import com.yuanfentiankon.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    @Query("SELECT m FROM Message m WHERE (m.sender.id = :userId1 AND m.receiver.id = :userId2) " +
           "OR (m.sender.id = :userId2 AND m.receiver.id = :userId1) ORDER BY m.createdAt ASC")
    List<Message> findConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
    
    List<Message> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
    
    @Query("SELECT m FROM Message m WHERE m.receiver.id = :userId AND m.read = false")
    List<Message> findUnreadMessages(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(m) FROM Message m WHERE m.receiver.id = :userId AND m.read = false")
    Integer countUnreadMessages(@Param("userId") Long userId);
    
    @Query(value = "SELECT m.* FROM message m " +
                  "WHERE ((m.sender_id = :userId AND m.receiver_id = c.contact_id) " +
                  "OR (m.sender_id = c.contact_id AND m.receiver_id = :userId)) " +
                  "ORDER BY m.created_at DESC LIMIT 1", 
           nativeQuery = true)
    List<Message> findLatestMessages(@Param("userId") Long userId);

    // 修改这两个方法，分别查询发送者和接收者
    @Query("SELECT DISTINCT m.sender FROM Message m WHERE m.receiver.id = :userId")
    List<Object> findSendersByReceiverId(@Param("userId") Long userId);

    @Query("SELECT DISTINCT m.receiver FROM Message m WHERE m.sender.id = :userId")
    List<Object> findReceiversBySenderId(@Param("userId") Long userId);
}
package com.megatravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.model.system_user_info.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
	
	List<Message> findAllBySenderId(Long id);
	
	List<Message> findAllByReceiverId(Long id);
	
	List<Message> findAllByChat_Id(Long chatId);
		
	List<Message> findAllByChat_IdOrderByDateDesc(Long chatId);
	
	Message findFirstByChat_IdOrderByDateDesc(Long chatId);

}

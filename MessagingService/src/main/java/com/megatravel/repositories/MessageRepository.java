package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.system_user_info.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
	
	List<Message> findAllBySenderId(Long id);
	
	List<Message> findAllByReceiverId(Long id);
	
	List<Message> findAllByChat_Id(Long chatId);
		
	@Query("Select m from Message m where m.chat.id = ?1 order by m.date ASC")
	List<Message> findAllByChat_IdOrderByDateDesc(Long chatId);
	
	Message findFirstByChat_IdOrderByDateDesc(Long chatId);

	List<Message> findAllByLastChangedTimeBetween(Date start, Date end);

}

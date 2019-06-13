package com.megatravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.system_user_info.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>  {

	@Query("Select c from Chat c where c.id in"
	  + " (Select distinct m.chat.id from Message m where m.sender.id = ?1 or m.receiver.id = ?1 order by m.date asc)")
	List<Chat> allChats(Long id);
	
	
	
}

package com.megatravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.megatravel.model.system_user_info.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>  {

	@Query("Select c from chat c where c.id in"
		+ " (select m.chat from Message m where m.sender = :Id and m.receiver = :Id)")
	List<Chat> findAllChatsForUser(@Param("Id")Long id);
}

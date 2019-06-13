package com.megatravel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.megatravel.model.system_user_info.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
	
	List<Message> findAllBySenderId(Long id);
	
	List<Message> findAllByReciverId(Long id);
	


}

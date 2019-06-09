package com.megatravel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.megatravel.model.system_user_info.Chat;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>  {

}

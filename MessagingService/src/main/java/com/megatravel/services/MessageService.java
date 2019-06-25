package com.megatravel.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.controllers.feign.FeignUser;
import com.megatravel.dtosoap.system_user_info.MessageDTO;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.system_user_info.Chat;
import com.megatravel.model.system_user_info.Message;
import com.megatravel.model.system_user_info.User;
import com.megatravel.repositories.ChatRepository;
import com.megatravel.repositories.HotelRepository;
import com.megatravel.repositories.MessageRepository;
import com.megatravel.repositories.UserRepository;

@Service
public class MessageService {
	
		@Autowired
		private MessageRepository messageRepository;
		
		@Autowired
		private ChatRepository chatRepository;
		
		@Autowired
		private HotelRepository hotelRepository;
		
		@Autowired
		private UserRepository userRepository;
		
		@SuppressWarnings("unused")
		@Autowired
		private FeignUser feignUser;		   
	    
		public List<Chat> getInbox(Long userId) {
			if(userId == null)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested null");
			List<Chat> chats = chatRepository.allChats(userId);
			return chats;
		}

		
		public List<Message> getMessages(Long userId,Long chatId) {
			if(chatId == null)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested null");
			List<Message> got = messageRepository.findAllByChat_IdOrderByDateDesc(chatId);
			if(got.size()>0)
				if(got.get(0).getReceiver().getId().equals(userId))
				{
					markRead(chatId);
				}
			return got;
		}

		/**
		 * Za MessageDTO neophodno popuniti Text,Caption,Sender.Id
		 * Stari chat => ChatId proslediti, HotelId = -1
		 * Novi chat => ChatId= -1, HotelId proslediti
		 */
		public Boolean sendMessage(Long chatId, Long hotelId, MessageDTO message) {
			
			if(message == null)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No MessageDTO payload sent");
			if(message.getText().length()==0 || message.getSenderDTO() == null)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid MessageDTO payload sent");
				
			
			Message sending = new Message(message);

			Optional<User> sender =  userRepository.findById(message.getSenderDTO().getId());  //feignUser.getUserFeign(message.getSender().getId()); 
			Optional<Chat> chat = chatRepository.findById(chatId);
			Optional<Hotel> hotel = hotelRepository.findById(hotelId);
			
			if(!sender.isPresent())
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Users sent not valid");
			
			Date date = new Date();
			sending.setDate(date);
			User receiver = null;
			
			if(!chat.isPresent()) //Pravi novi chat
			{
				Chat newChat = new Chat();		
				if(!hotel.isPresent())
					throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid hotel id sent");
				
				newChat.setChatsHotel(hotel.get());
				Set<Message> newMessages = new HashSet<>();
				newMessages.add(sending);
				newChat.setMessages(newMessages);
				chatRepository.save(newChat);
				sending.setChat(newChat);
				receiver = hotel.get().getUsersHotel();
			}
			else //Nastavlja chat
			{
				receiver = chat.get().getChatsHotel().getUsersHotel(); 
				sending.setChat(chat.get());
			}
				
			sending.setOpened(false);
			sending.setReceiver(receiver);
			sending.setSender(sender.get());
			messageRepository.save(sending);
			return true;
		}

		public Boolean markRead(Long chatId) {
			Message found = messageRepository.findFirstByChat_IdOrderByDateDesc(chatId);
			if(found == null)
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid chatId sent to mark read");
			found.setOpened(true);
			messageRepository.save(found);
			return true;				
		}

		public List<Chat> getChatsForSync(Date start, Date end) {
			List<Chat> chats = chatRepository.findAllByLastChangedTimeBetween(start, end);
			return chats;
		}

		public List<Message> getMessagesForSync(Date start, Date end) {
			List<Message> messages = this.messageRepository.findAllByLastChangedTimeBetween(start, end);
			return messages;
		}
}
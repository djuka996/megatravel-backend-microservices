package com.megatravel.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.system_user_info.ChatDTO;
import com.megatravel.dtosoap.system_user_info.MessageDTO;
import com.megatravel.interfaces.MessageService;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.system_user_info.Chat;
import com.megatravel.model.system_user_info.Message;
import com.megatravel.model.system_user_info.User;
import com.megatravel.repositories.ChatRepository;
import com.megatravel.repositories.HotelRepository;
import com.megatravel.repositories.MessageRepository;
import com.megatravel.repositories.UserRepository;


@WebService(portName="MessageServicePort",
serviceName="MessageService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.MessageService")
@Service
public class MessageServiceImpl implements MessageService {

	public static final String ENDPOINT = "/inbox";
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    public MessageServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	   
    
	@Override
	@WebMethod
	public List<ChatDTO> getInbox(Long userId) {
		List<Chat> chats = chatRepository.allChats(userId);
		List<ChatDTO> returning = new ArrayList<>();
		for (Chat chat : chats) {
			ChatDTO toAdd = new ChatDTO(chat);
			returning.add(toAdd);
		}
		return returning;
	}

	
	@Override
	@WebMethod
	public List<MessageDTO> getMessages(Long userId,Long chatId) {
		List<Message> got = messageRepository.findAllByChat_IdOrderByDateDesc(chatId);
		List<MessageDTO> returning = new ArrayList<>();
		for (Message message : got) {
			returning.add(new MessageDTO(message));
		}
		if(got.size()>0)
			if(got.get(0).getReceiver().getId().equals(userId))
			{
				markRead(chatId);
			}
		return returning;
	}

	@Override
	@WebMethod
	/**
	 * Za MessageDTO neophodno popuniti Text,Caption,Sender.Id
	 * Stari chat => ChatId proslediti, HotelId = -1
	 * Novi chat => ChatId= -1, HotelId proslediti
	 */
	public Boolean sendMessage(Long chatId, Long hotelId, MessageDTO message) {
		
		if(message == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No MessageDTO payload sent");
		if(message.getText().length()==0 || message.getSender() == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid MessageDTO payload sent");
			
		
		Message sending = new Message(message);

		Optional<User> sender = userRepository.findById(message.getSender().getId());
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

	@Override
	@WebMethod
	public Boolean markRead(Long chatId) {
		Message found = messageRepository.findFirstByChat_IdOrderByDateDesc(chatId);
		if(found != null)
		{
			found.setOpened(true);
			messageRepository.save(found);
			return true;
		}
		else 
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Invalid chatId sent to mark read");
	}


	@Override
	@WebMethod
	public List<ChatDTO> getChatsForSync(Date start, Date end) {
		List<Chat> chats = chatRepository.findAllByLastChangedTimeBetween(start, end);
		List<ChatDTO> result = new ArrayList<ChatDTO>();
		for(Chat chat : chats)
			result.add(new ChatDTO(chat));
		return result;
	}


	@Override
	public List<MessageDTO> getMessagesForSync(Date start, Date end) {
		List<Message> messages = this.messageRepository.findAllByLastChangedTimeBetween(start, end);
		List<MessageDTO> result = new ArrayList<MessageDTO>();
		for(Message message : messages)
			result.add(new MessageDTO(message));
		return result;
	}
	
}

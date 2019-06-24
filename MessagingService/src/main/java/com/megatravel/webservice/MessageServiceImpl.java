package com.megatravel.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.system_user_info.ChatDTO;
import com.megatravel.dtosoap.system_user_info.MessageDTO;
import com.megatravel.interfaces.MessageServiceInterface;
import com.megatravel.model.system_user_info.Chat;
import com.megatravel.model.system_user_info.Message;
import com.megatravel.services.MessageService;


@WebService(portName="MessageServicePort",
serviceName="MessageService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.MessageServiceInterface")
@Component
public class MessageServiceImpl implements MessageServiceInterface {

	public static final String ENDPOINT = "/inbox";
	
	@Autowired
	private MessageService messageService;
	
    public MessageServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	   
    
	@Override
	public List<ChatDTO> getInbox(Long userId) {
		return convertChatToListDTO(messageService.getInbox(userId));
	}

	
	@Override
	public List<MessageDTO> getMessages(Long userId,Long chatId) {
		return convertMessageToListDTO(messageService.getMessages(userId, chatId));
	}

	@Override
	/**
	 * Za MessageDTO neophodno popuniti Text,Caption,Sender.Id
	 * Stari chat => ChatId proslediti, HotelId = -1
	 * Novi chat => ChatId= -1, HotelId proslediti
	 */
	public Boolean sendMessage(Long chatId, Long hotelId, MessageDTO message) {
		return messageService.sendMessage(chatId, hotelId, message);
	}

	@Override
	public Boolean markRead(Long chatId) {
		return messageService.markRead(chatId);
	}


	@Override
	public List<ChatDTO> getChatsForSync(Date start, Date end) {
		return convertChatToListDTO(messageService.getChatsForSync(start, end));
	}


	@Override
	public List<MessageDTO> getMessagesForSync(Date start, Date end) {
		return convertMessageToListDTO(messageService.getMessagesForSync(start, end));
	}
	
	private List<MessageDTO> convertMessageToListDTO(List<Message> got){
		List<MessageDTO> returning = new ArrayList<>();
		for (Message iter : got) {
			MessageDTO toAdd = new MessageDTO(iter);
			returning.add(toAdd);
		}
		return returning;
	}
	
	
	private List<ChatDTO> convertChatToListDTO(List<Chat> got){
		List<ChatDTO> returning = new ArrayList<>();
		for (Chat iter : got) {
			ChatDTO toAdd = new ChatDTO(iter);
			returning.add(toAdd);
		}
		return returning;
	}
	
}

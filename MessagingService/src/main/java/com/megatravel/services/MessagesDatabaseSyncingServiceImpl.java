package com.megatravel.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.system_user_info.ChatDTO;
import com.megatravel.dtosoap.system_user_info.MessageDTO;
import com.megatravel.interfaces.MessagesDatabaseSyncingService;
import com.megatravel.model.system_user_info.Chat;
import com.megatravel.model.system_user_info.Message;
import com.megatravel.repositories.ChatRepository;
import com.megatravel.repositories.MessageRepository;

@WebService(portName="MessagesDatabaseSyncingServicePort",
			serviceName="MessagesDatabaseSyncingService",
			targetNamespace="http://interfaces.megatravel.com/",
			endpointInterface = "com.megatravel.interfaces.MessagesDatabaseSyncingService")
@Component
public class MessagesDatabaseSyncingServiceImpl implements MessagesDatabaseSyncingService {

	public static final String ENDPOINT = "/services/sync";

	@Autowired
	private MessageRepository messagesRepository;
	
	@Autowired
	private ChatRepository chatsRepository;
	
    public MessagesDatabaseSyncingServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	
	@Override
	public List<MessageDTO> getMessagesForSync(Date start, Date end) {
		List<Message> messages = this.messagesRepository.findAllByLastChangedTimeBetween(start, end);
		List<MessageDTO> result = new ArrayList<MessageDTO>();
		for(Message message : messages)
			result.add(new MessageDTO(message));
		return result;
	}

	@Override
	public List<ChatDTO> getChatsForSync(Date start, Date end) {
		List<Chat> chats = this.chatsRepository.findAllByLastChangedTimeBetween(start, end);
		List<ChatDTO> result = new ArrayList<ChatDTO>();
		for(Chat chat : chats)
			result.add(new ChatDTO(chat));
		return result;
	}

}

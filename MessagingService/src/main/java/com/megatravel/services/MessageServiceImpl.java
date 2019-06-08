package com.megatravel.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dto.system_user_info.ChatDTO;
import com.megatravel.dto.system_user_info.MessageDTO;
import com.megatravel.dto.system_user_info.SystemUserInfoDTO;
import com.megatravel.interfaces.MessageService;


@WebService(endpointInterface = "com.megatravel.interfaces.MessageService")
@Service
public class MessageServiceImpl implements MessageService {

	public static final String ENDPOINT = "/inbox";
	
	//@Autowired
	//private MessageRepository messageRepository;
	//TODO dodati kada se doda model
	
    public MessageServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	
    //TODO remove examples methods
    private List<ChatDTO> getInboxExample(){
    	List<ChatDTO> returning = new ArrayList<>();
		returning.add(getChatExample());		
		return returning;
    }
    
    private ChatDTO getChatExample() {
    	ChatDTO chatExample1 = new ChatDTO();
		chatExample1.getMessages().add(getMessageExample());
		return chatExample1;
    }
    
    private MessageDTO getMessageExample() {
		MessageDTO messageExample1 = new MessageDTO();
		messageExample1.setCaption("Caption mozda treba u chat");
		messageExample1.setDate(new Date());
		messageExample1.setId(0);
		messageExample1.setOpened(false);
		SystemUserInfoDTO user1 = new SystemUserInfoDTO();
		user1.setFirstName("User1");
		user1.setId(1);
		messageExample1.setSender(user1);
		
		SystemUserInfoDTO user2 = new SystemUserInfoDTO();
		user1.setFirstName("User2");
		user1.setId(2);
		messageExample1.setReceiver(user2);
		return messageExample1;
    }
  
    private List<MessageDTO> getMessagesExample(){
    	List<MessageDTO> messages = new ArrayList<>();
    	messages.add(getMessageExample());
    	messages.add(getMessageExample());
    	return messages;
    }
    
    
	@Override
	@WebMethod
	public List<ChatDTO> getInbox(Long userId) {
		// TODO Auto-generated method stub
		return getInboxExample();
	}

	@Override
	public ChatDTO getChat(Long userId, Long chatId) {
		// TODO Auto-generated method stub
		return getChatExample();
	}
	
	@Override
	@WebMethod
	public List<MessageDTO> getMessages(Long userId) {
		// TODO Auto-generated method stub
		return getMessagesExample();
	}

	@Override
	@WebMethod
	public Boolean sendMessage(Long userId, Long chatId, String message) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@WebMethod
	public Boolean markRead(Long UserId, Long chatId) {
		// TODO Auto-generated method stub
		return null;
	}

}

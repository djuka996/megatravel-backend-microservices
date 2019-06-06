package com.megatravel.MessagingService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.MessagingService.configurations.WebApplicationContextLocator;
import com.megatravel.MessagingService.interfaces.MessageService;


@WebService(endpointInterface = "com.megatravel.MessagingService.interfaces.MessageService")
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
	
	@Override
	@WebMethod
	public List<Boolean> getInbox(Long userId) {
		// TODO Auto-generated method stub
		List<Boolean> returning = new ArrayList<>();
		returning.add(true);
		return returning;
	}

	@Override
	@WebMethod
	public List<Boolean> getMessages(Long userId) {
		// TODO Auto-generated method stub
		return null;
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

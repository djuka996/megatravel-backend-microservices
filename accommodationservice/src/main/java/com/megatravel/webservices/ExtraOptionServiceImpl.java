package com.megatravel.webservices;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtos.ExtraOptionDTO;
import com.megatravel.interfaces.ExtraOptionServiceInterface;

@WebService(endpointInterface = "com.megatravel.accommodationservice.interfaces.ExtraOptionServiceInterface")
public class ExtraOptionServiceImpl implements ExtraOptionServiceInterface {

	public static final String ENDPOINT = "/services/extra-options";
	
	public ExtraOptionServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<ExtraOptionDTO> getRoomExtraOptions(Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtraOptionDTO getRoomExtraOption(Long id, Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtraOptionDTO createRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtraOptionDTO updateRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeExtraOption(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}

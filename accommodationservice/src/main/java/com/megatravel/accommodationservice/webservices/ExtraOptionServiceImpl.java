package com.megatravel.accommodationservice.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.accommodationservice.configurations.WebApplicationContextLocator;
import com.megatravel.accommodationservice.dtos.ExtraOptionDTO;
import com.megatravel.accommodationservice.interfaces.ExtraOptionServiceInterface;

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
	@WebMethod
	public List<ExtraOptionDTO> getRoomExtraOptions(Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ExtraOptionDTO getRoomExtraOption(Long id, Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ExtraOptionDTO createRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ExtraOptionDTO updateRoomExtraOption(ExtraOptionDTO extraOption, Long roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public boolean removeExtraOption(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}

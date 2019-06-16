package com.megatravel.webservices;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configurations.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.interfaces.ExtraOptionServiceInterface;
import com.megatravel.repositories.ExtraOptionRepository;

@WebService(portName="ExtraOptionServicePort",
serviceName="ExtraOptionService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.ExtraOptionServiceInterface")
public class ExtraOptionServiceImpl implements ExtraOptionServiceInterface {

	public static final String ENDPOINT = "/services/extra-options";
	
	@Autowired
	private ExtraOptionRepository extraOptionRepository;
	
	public ExtraOptionServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<ExtraOptionDTO> getRoomExtraOptions(Long roomId) {
		//extraOptionRepository.find
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

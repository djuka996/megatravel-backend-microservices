package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.interfaces.ExtraOptionServiceInterface;
import com.megatravel.model.hotel.ExtraOption;
import com.megatravel.services.ExtraOptionService;

@WebService(portName="ExtraOptionServicePort",
serviceName="ExtraOptionService",
targetNamespace="http://interfaces.megatravel.com/",
endpointInterface = "com.megatravel.interfaces.ExtraOptionServiceInterface")
@Component
public class ExtraOptionServiceImpl implements ExtraOptionServiceInterface {

	public static final String ENDPOINT = "/services/extra-options";
	
	@Autowired
	private ExtraOptionService extraOptionService;
	
	public ExtraOptionServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
	}
	
	@Override
	public List<ExtraOptionDTO> getAllExtraOptions() {
		List<ExtraOption> got = extraOptionService.getAllExtraOptions();
		return convertToListDTO(got);
	}

	@Override
	public List<ExtraOptionDTO> getHotelExtraOptions(Long hotelId) {
		List<ExtraOption> got = extraOptionService.getHotelExtraOption(hotelId);
		return convertToListDTO(got);
	}
	
	@Override
	public List<ExtraOptionDTO> getRoomExtraOptions(Long roomId) {
		List<ExtraOption> got = extraOptionService.getRoomExtraOptions(roomId);
		return convertToListDTO(got);
	}

	@Override
	public ExtraOptionDTO getRoomExtraOption(Long id) {
		return new ExtraOptionDTO(extraOptionService.getExtraOption(id));
	}

	@Override
	public ExtraOptionDTO createRoomExtraOption(ExtraOptionDTO extraOption) {
		return new ExtraOptionDTO(extraOptionService.createRoomExtraOption(extraOption));
	}

	@Override
	public ExtraOptionDTO updateRoomExtraOption(ExtraOptionDTO extraOption) {
		return new ExtraOptionDTO(extraOptionService.updateRoomExtraOption(extraOption));
	}

	@Override
	public boolean removeExtraOption(Long id) {
		return extraOptionService.removeExtraOption(id);
	}

	private List<ExtraOptionDTO> convertToListDTO(List<ExtraOption> got) {
		List<ExtraOptionDTO> ret = new ArrayList<>();
		for (ExtraOption extraOption : got) {
			ret.add(new ExtraOptionDTO(extraOption));
		}
		return ret;
	}
}

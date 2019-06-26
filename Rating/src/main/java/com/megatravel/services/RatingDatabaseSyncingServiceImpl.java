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
import com.megatravel.dtosoap.system_user_info.UserReviewDTO;
import com.megatravel.interfaces.RatingDatabaseSyncingService;
import com.megatravel.model.system_user_info.UserReview;
import com.megatravel.repositories.RatingRepository;

@WebService(portName="RatingDatabaseSyncingServicePort",
			serviceName="RatingDatabaseSyncingService",
			targetNamespace="http://interfaces.megatravel.com/",
			endpointInterface = "com.megatravel.interfaces.RatingDatabaseSyncingService")
@Component
public class RatingDatabaseSyncingServiceImpl implements RatingDatabaseSyncingService {

	public static final String ENDPOINT = "/services/sync";
	
	@Autowired
	private RatingRepository ratingsRepository;
	
    public RatingDatabaseSyncingServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	
	@Override
	public List<UserReviewDTO> getReviewsForSync(Date start, Date end) {
		List<UserReview> reviews = this.ratingsRepository.findAllByLastChangedTimeBetween(start, end);
		List<UserReviewDTO> result = new ArrayList<UserReviewDTO>();
		for(UserReview review : reviews)
			result.add(new UserReviewDTO(review));
		return result;
	}

}

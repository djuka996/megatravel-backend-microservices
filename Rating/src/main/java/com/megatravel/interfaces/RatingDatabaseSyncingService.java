package com.megatravel.interfaces;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtosoap.system_user_info.UserReviewDTO;

@WebService
public interface RatingDatabaseSyncingService {

	@WebMethod(operationName = "ratingsChange")
	List<UserReviewDTO> getReviewsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
}

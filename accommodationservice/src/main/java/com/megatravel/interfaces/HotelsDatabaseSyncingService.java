package com.megatravel.interfaces;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.dtosoap.hotel.ImageDTO;
import com.megatravel.dtosoap.hotel.PriceListDTO;
import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.dtosoap.hotel.UnitPriceInformationDTO;
import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;

@WebService
public interface HotelsDatabaseSyncingService {

	@WebMethod(operationName = "addressesChanges")
	List<AddressDTO> getAddressesForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "hotelsChanges")
	List<HotelDTO> getHotelsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "accomodationsChanges")
	List<AccomodationTypeDTO> getAccomodationsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "extraOptionsChanges")
	List<ExtraOptionDTO> getExtraOptionsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "imagesChanges")
	List<ImageDTO> getImagesForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "priceListsChanges")
	List<PriceListDTO> getPriceListsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "roomsChanges")
	List<RoomDTO> getRoomsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "unitPriceInformationsChanges")
	List<UnitPriceInformationDTO> getUnitPriceInformationsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
	@WebMethod(operationName = "roomReservationChanges")
	List<RoomReservationDTO> getRoomReservationsForSync(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end);
	
}

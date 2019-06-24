package com.megatravel.webservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.configuration.WebApplicationContextLocator;
import com.megatravel.dtosoap.global_parameters.AddressDTO;
import com.megatravel.dtosoap.hotel.AccomodationTypeDTO;
import com.megatravel.dtosoap.hotel.ExtraOptionDTO;
import com.megatravel.dtosoap.hotel.HotelDTO;
import com.megatravel.dtosoap.hotel.ImageDTO;
import com.megatravel.dtosoap.hotel.PriceListDTO;
import com.megatravel.dtosoap.hotel.RoomDTO;
import com.megatravel.dtosoap.hotel.UnitPriceInformationDTO;
import com.megatravel.dtosoap.room_reservation.RoomReservationDTO;
import com.megatravel.interfaces.HotelsDatabaseSyncingService;
import com.megatravel.model.global_parameters.Address;
import com.megatravel.model.hotel.AccomodationType;
import com.megatravel.model.hotel.ExtraOption;
import com.megatravel.model.hotel.Hotel;
import com.megatravel.model.hotel.Image;
import com.megatravel.model.hotel.PriceList;
import com.megatravel.model.hotel.Room;
import com.megatravel.model.hotel.UnitPriceInformation;
import com.megatravel.model.room_reservation.RoomReservation;
import com.megatravel.repositories.AccommodationTypeRepository;
import com.megatravel.repositories.AddressRepository;
import com.megatravel.repositories.ExtraOptionRepository;
import com.megatravel.repositories.HotelRepository;
import com.megatravel.repositories.ImageRepository;
import com.megatravel.repositories.PriceListRepository;
import com.megatravel.repositories.RoomRepository;
import com.megatravel.repositories.RoomReservationRepository;
import com.megatravel.repositories.UnitPriceRepository;

@WebService(portName="HotelsDatabaseSyncingServicePort",
			serviceName="HotelsDatabaseSyncingService",
			targetNamespace="http://interfaces.megatravel.com/",
			endpointInterface = "com.megatravel.interfaces.HotelsDatabaseSyncingService")
@Component
public class HotelsDatabaseSyncingServiceImpl implements HotelsDatabaseSyncingService {

	public static final String ENDPOINT = "/services/sync";
	
	@Autowired
	private AddressRepository addressesRepository;
	
	@Autowired
	private HotelRepository hotelsRepository;
	
	@Autowired
	private AccommodationTypeRepository accommodationTypesRepository;
	
	@Autowired
	private ExtraOptionRepository extraOptionsRepository;
	
	@Autowired
	private ImageRepository imagesRepository;
	
	@Autowired
	private PriceListRepository priceListsRepository;
	
	@Autowired
	private RoomRepository roomsRepository;
	
	@Autowired
	private UnitPriceRepository unitPricesRepository;
	
	@Autowired
	private RoomReservationRepository roomReservationsRepository;
	
    public HotelsDatabaseSyncingServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }
	
	@Override
	public List<AddressDTO> getAddressesForSync(Date start, Date end) {
		List<Address> addresses = this.addressesRepository.findAllByLastChangedTimeBetween(start, end);
		List<AddressDTO> result = new ArrayList<AddressDTO>();
		for(Address address : addresses)
			result.add(new AddressDTO(address));
		return result;
	}

	@Override
	public List<HotelDTO> getHotelsForSync(Date start, Date end) {
		List<Hotel> hotels = this.hotelsRepository.findAllByLastChangedTimeBetween(start, end);
		List<HotelDTO> result = new ArrayList<HotelDTO>();
		for(Hotel hotel : hotels)
			result.add(new HotelDTO(hotel));
		return result;
	}

	@Override
	public List<AccomodationTypeDTO> getAccomodationsForSync(Date start, Date end) {
		List<AccomodationType> types = this.accommodationTypesRepository.findAllByLastChangedTimeBetween(start, end);
		List<AccomodationTypeDTO> result = new ArrayList<AccomodationTypeDTO>();
		for(AccomodationType type : types)
			result.add(new AccomodationTypeDTO(type));
		return result;
	}

	@Override
	public List<ExtraOptionDTO> getExtraOptionsForSync(Date start, Date end) {
		List<ExtraOption> extraOptions = this.extraOptionsRepository.findAllByLastChangedTimeBetween(start, end);
		List<ExtraOptionDTO> result = new ArrayList<ExtraOptionDTO>();
		for(ExtraOption extraOption : extraOptions)
			result.add(new ExtraOptionDTO(extraOption));
		return result;
	}

	@Override
	public List<ImageDTO> getImagesForSync(Date start, Date end) {
		List<Image> images = this.imagesRepository.findAllByLastChangedTimeBetween(start, end);
		List<ImageDTO> result = new ArrayList<ImageDTO>();
		for(Image image : images)
			result.add(new ImageDTO(image));
		return result;
	}

	@Override
	public List<PriceListDTO> getPriceListsForSync(Date start, Date end) {
		List<PriceList> priceLists = this.priceListsRepository.findAllByLastChangedTimeBetween(start, end);
		List<PriceListDTO> result = new ArrayList<PriceListDTO>();
		for(PriceList priceList : priceLists)
			result.add(new PriceListDTO());
		return result;
	}

	@Override
	public List<RoomDTO> getRoomsForSync(Date start, Date end) {
		List<Room> rooms = this.roomsRepository.findAllByLastChangedTimeBetween(start, end);
		List<RoomDTO> result = new ArrayList<RoomDTO>();
		for(Room room : rooms)
			result.add(new RoomDTO(room));
		return result;
	}

	@Override
	public List<UnitPriceInformationDTO> getUnitPriceInformationsForSync(Date start, Date end) {
		List<UnitPriceInformation> informations = this.unitPricesRepository.findAllByLastChangedTimeBetween(start, end);
		List<UnitPriceInformationDTO> result = new ArrayList<UnitPriceInformationDTO>();
		for(UnitPriceInformation information : informations)
			result.add(new UnitPriceInformationDTO());
		return result;
	}

	@Override
	public List<RoomReservationDTO> getRoomReservationsForSync(Date start, Date end) {
		List<RoomReservation> reservations = this.roomReservationsRepository.findAllByLastChangedTimeBetween(start, end);
		List<RoomReservationDTO> result = new ArrayList<RoomReservationDTO>();
		for(RoomReservation reservation : reservations)
			result.add(new RoomReservationDTO(reservation));
		return result;
	}

}

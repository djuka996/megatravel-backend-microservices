package com.megatravel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.global_parameters.AmountType;

@Repository
public interface AmountTypeRepository extends JpaRepository<AmountType, Long> {
	
	@Query("Select amt from AmountType amt where"
			+ " amt.id in (Select cp.id from CurrencyPrice cp where"
						+ " cp.id in (Select unit.price.id from UnitPriceInformation unit where "
									+ " unit.priceList.id in (Select p.id from PriceList p where p.hotelPriceList.id in "
												+ "(SELECT r.roomsHotel.id FROM Room r WHERE r.id = ?1) "
												+ "AND sysdate() between p.beginDate and p.endDate ) "
									+ " AND unit.room.id = ?1 ))")
	AmountType findCurrentlyPriced(Long roomId);
}

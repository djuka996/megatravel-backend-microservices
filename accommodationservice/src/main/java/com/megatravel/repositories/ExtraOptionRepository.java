package com.megatravel.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.megatravel.model.hotel.ExtraOption;

@Repository
public interface ExtraOptionRepository extends JpaRepository<ExtraOption,Long> {
	
	@Query("Select eo from ExtraOption eo where eo.id in "
			+ "(Select heo.extraOption.id from HotelExtraOption heo where heo.hotelExtraOption.id = ?1)")
	List<ExtraOption> findAllForHotel(Long id);

	List<ExtraOption> findAllByLastChangedTimeBetween(Date start, Date end);
}

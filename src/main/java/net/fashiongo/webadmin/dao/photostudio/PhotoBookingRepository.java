package net.fashiongo.webadmin.dao.photostudio;

import java.util.List;

import net.fashiongo.webadmin.model.photostudio.PhotoBooking;

import org.springframework.data.repository.CrudRepository;

public interface PhotoBookingRepository extends CrudRepository<PhotoBooking, Integer>{
	List<PhotoBooking> findByModelScheduleID(Integer modelScheduleID);
}

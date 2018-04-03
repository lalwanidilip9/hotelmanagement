package online.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.hotelmanagement.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	@Query("from Booking b where b.bookingStatus = :bookingStatus")
    List<Booking> findByBookingStatus(@Param("bookingStatus") int bookingStatus);
	
	@Query("from Booking b where b.bookingStatus = :bookingStatus AND b.roomTypeId = :roomTypeId")
    List<Booking> findByBookingStatusAndRoomTypeId(@Param("bookingStatus") int bookingStatus, @Param("roomTypeId") Long roomTypeId);
	
	@Query("from Booking b where b.bookingId = :bookingId")
    Booking findByBookingId(@Param("bookingId") Long bookingId);
	
	@Query("from Booking b where b.userId = :userId")
	List<Booking> findByUserId(@Param("userId") Long userId);
}

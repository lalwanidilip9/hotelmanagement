package online.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.hotelmanagement.entity.BookedRoom;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long>{
	@Query("from BookedRoom b where b.bookingId = :bookingId")
	List<BookedRoom> findByBookingId(@Param("bookingId") Long bookingId);
}

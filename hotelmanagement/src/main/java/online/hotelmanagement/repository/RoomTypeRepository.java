package online.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.hotelmanagement.entity.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
	@Query("from RoomType r where r.roomTypeId = :roomTypeId")
	RoomType findByRoomTypeId(@Param("roomTypeId") Long roomTypeId);
	
	@Query("from RoomType r where r.noOfMaxAdults >= :noOfGuests")
	List<RoomType> findByEventRoomTypeId(@Param("noOfGuests") int noOfGuests);
}

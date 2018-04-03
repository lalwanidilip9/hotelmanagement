package online.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.hotelmanagement.entity.RoomAmenity;

@Repository
public interface RoomAmenityRepository extends JpaRepository<RoomAmenity, Long> {
	@Query("from RoomAmenity ra where ra.roomTypeId = :roomTypeId")
	List<RoomAmenity> findAmenityByRoomTypeId(@Param("roomTypeId") Long roomTypeId);
}

package online.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import online.hotelmanagement.entity.RoomImages;

public interface RoomImagesRepository  extends JpaRepository<RoomImages, Long> {
	@Query("from RoomImages ri where ri.roomTypeId = :roomTypeId")
	List<RoomImages> findRoomImagesByRoomTypeId(@Param("roomTypeId") Long roomTypeId);
}

package online.hotelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import online.hotelmanagement.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	@Query("from Room r where r.roomTypeId = :roomTypeId")
    List<Room> findByRoomTypeId(@Param("roomTypeId") Long roomTypeId);
	
	@Query("from Room r where r.roomId = :roomId")
	Room findByRoomId(@Param("roomId") Long roomId);
}

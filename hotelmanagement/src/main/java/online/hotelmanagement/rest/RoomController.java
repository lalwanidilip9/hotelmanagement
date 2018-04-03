package online.hotelmanagement.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import online.hotelmanagement.entity.Room;
import online.hotelmanagement.entity.RoomSearch;
import online.hotelmanagement.service.RoomService;

@RestController
@RequestMapping(value = "/")
public class RoomController {
	@Autowired
	RoomService roomService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/room/search")
	public List<Room> getRooms(@RequestBody RoomSearch roomSearch){
		return roomService.getRooms(
				LocalDate.parse(roomSearch.getStartDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")), 
				LocalDate.parse(roomSearch.getEndDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
	}
}

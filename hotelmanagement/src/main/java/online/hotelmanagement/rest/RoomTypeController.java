package online.hotelmanagement.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import online.hotelmanagement.entity.EventRoomSearch;
import online.hotelmanagement.entity.ImageGrid;
import online.hotelmanagement.entity.RoomAmenity;
import online.hotelmanagement.entity.RoomImages;
import online.hotelmanagement.entity.RoomSearch;
import online.hotelmanagement.entity.RoomType;
import online.hotelmanagement.service.RoomTypeService;

@RestController
@RequestMapping(value = "/")
public class RoomTypeController {
	@Autowired
	RoomTypeService service;

	@RequestMapping(method = RequestMethod.POST, value = "/roomtype/save")
	public void saveRoomType(@RequestBody RoomType roomType) {
		service.saveRoomType(roomType);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/roomtype/image/delete")
	public void deleteRoomTypeImage(@RequestBody String imageId) {
		Long roomTypeImageId;
		try {
			roomTypeImageId = Long.parseLong(imageId);
		} catch (Exception e) {
			roomTypeImageId = (long) 0;
		}
		if(roomTypeImageId != 0){
			service.deleteRoomTypeImage(roomTypeImageId);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/roomtype/{typeId}")
	public RoomType getRoomTypeDetails(@PathVariable String typeId) {
		Long roomTypeId;
		try {
			roomTypeId = Long.parseLong(typeId);
		} catch (Exception e) {
			return null;
		}
		return service.getRoomType(roomTypeId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/roomtype/all")
	public List<RoomType> getAllRoomTypes() {
		return service.getAllRoomTypes();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/roomtype/search")
	public List<RoomType> getRooms(@RequestBody RoomSearch roomSearch) {
		return service.searchRoomTypes(
				LocalDate.parse(roomSearch.getStartDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")),
				LocalDate.parse(roomSearch.getEndDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")),
				roomSearch.getNoOfAdults(), roomSearch.getNoOfChildren(), roomSearch.getNoOfRooms());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/roomtype/images/{typeId}")
	public List<ImageGrid> getRoomImages(@PathVariable String typeId) {
		Long roomTypeId;
		try {
			roomTypeId = Long.parseLong(typeId);
		} catch (Exception e) {
			return null;
		}
		return setRoomImages(roomTypeId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/roomtype/imagesforpopup/{typeId}")
	public List<RoomImages> getRoomImagesForPopup(@PathVariable String typeId) {
		Long roomTypeId;
		try {
			roomTypeId = Long.parseLong(typeId);
		} catch (Exception e) {
			return null;
		}
		return service.getRoomImagesForPopup(roomTypeId);
	}

	private List<ImageGrid> setRoomImages(Long roomTypeId) {
		List<RoomImages> images = service.getRoomImages(roomTypeId);
		List<ImageGrid> imageGrid = new ArrayList<>();

		ImageGrid grid = new ImageGrid();
		if (images.size() > 0) {
			grid.setImage1(images.get(0).getImagePath());
			grid.setImageId1(images.get(0).getRoomImageId());
		}
		else{
			return imageGrid;
		}
		if (images.size() > 1) {
			grid.setImage2(images.get(1).getImagePath());
			grid.setImageId2(images.get(1).getRoomImageId());
		}
		if (images.size() > 2) {
			grid.setImage3(images.get(2).getImagePath());
			grid.setImageId3(images.get(2).getRoomImageId());
		}
		if (images.size() > 3) {
			grid.setImage4(images.get(3).getImagePath());
			grid.setImageId4(images.get(3).getRoomImageId());
		}
		if (images.size() > 4) {
			grid.setImage5(images.get(4).getImagePath());
			grid.setImageId5(images.get(4).getRoomImageId());
		}
		if (images.size() > 5) {
			grid.setImage6(images.get(5).getImagePath());
			grid.setImageId6(images.get(5).getRoomImageId());
		}
		imageGrid.add(grid);
		
		ImageGrid grid1 = new ImageGrid();
		if (images.size() > 6) {
			grid1.setImage1(images.get(6).getImagePath());
			grid1.setImageId1(images.get(6).getRoomImageId());
		}
		else{
			return imageGrid;
		}
		if (images.size() > 7) {
			grid1.setImage2(images.get(7).getImagePath());
			grid1.setImageId2(images.get(7).getRoomImageId());
		}
		if (images.size() > 8) {
			grid1.setImage3(images.get(8).getImagePath());
			grid1.setImageId3(images.get(8).getRoomImageId());
		}
		if (images.size() > 9) {
			grid1.setImage4(images.get(9).getImagePath());
			grid1.setImageId4(images.get(9).getRoomImageId());
		}
		if (images.size() > 10) {
			grid1.setImage5(images.get(10).getImagePath());
			grid1.setImageId5(images.get(10).getRoomImageId());
		}
		if (images.size() > 11) {
			grid1.setImage6(images.get(11).getImagePath());
			grid1.setImageId6(images.get(11).getRoomImageId());
		}
		imageGrid.add(grid1);
		
		ImageGrid grid2 = new ImageGrid();
		if (images.size() > 12) {
			grid2.setImage1(images.get(12).getImagePath());
			grid2.setImageId1(images.get(12).getRoomImageId());
		}
		else{
			return imageGrid;
		}
		if (images.size() > 13) {
			grid2.setImage2(images.get(13).getImagePath());
			grid2.setImageId2(images.get(13).getRoomImageId());
		}
		if (images.size() > 14) {
			grid2.setImage3(images.get(14).getImagePath());
			grid2.setImageId3(images.get(14).getRoomImageId());
		}
		if (images.size() > 15) {
			grid2.setImage4(images.get(15).getImagePath());
			grid2.setImageId4(images.get(15).getRoomImageId());
		}
		if (images.size() > 16) {
			grid2.setImage5(images.get(16).getImagePath());
			grid2.setImageId5(images.get(16).getRoomImageId());
		}
		if (images.size() > 17) {
			grid2.setImage6(images.get(17).getImagePath());
			grid2.setImageId6(images.get(17).getRoomImageId());
		}
		imageGrid.add(grid2);
		return imageGrid;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/roomtype/amenities/{typeId}")
	public List<RoomAmenity> getRoomTypeAmenities(@PathVariable String typeId) {
		Long roomTypeId;
		try {
			roomTypeId = Long.parseLong(typeId);
		} catch (Exception e) {
			return null;
		}
		return service.getRoomTypeAmenities(roomTypeId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/event/search")
	public List<RoomType> getRooms(@RequestBody EventRoomSearch eventRoomSearch) {
		return service.searchEventHalls(
				LocalDate.parse(eventRoomSearch.getStartDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")),
				LocalDate.parse(eventRoomSearch.getEndDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")),
				eventRoomSearch.getNoOfGuests());
	}
}

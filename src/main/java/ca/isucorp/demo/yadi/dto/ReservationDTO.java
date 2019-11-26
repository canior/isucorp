package ca.isucorp.demo.yadi.dto;

import lombok.Data;


@Data
public class ReservationDTO {
	private Long id;
	
	private ContactDTO contact;
	
	private PlaceDTO place;
	
	private String visitDateTime;
	
	private String description;
	
	private boolean favorite;
	
}

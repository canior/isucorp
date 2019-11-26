package ca.isucorp.demo.yadi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Contact contact;

	@ManyToOne
	private Place place;

	private LocalDate visitDateTime;
	
	private String description;

	private boolean favorite;
	
	public Reservation() {
		this.setFavorite(false);
	}
	
	public Reservation(Contact contact, Place place, LocalDate visitDateTime, String description) {
		this.setContact(contact);
		this.setPlace(place);
		this.setVisitDateTime(visitDateTime);
		this.setDescription(description);
	}

}

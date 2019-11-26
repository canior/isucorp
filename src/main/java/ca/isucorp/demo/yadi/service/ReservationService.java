package ca.isucorp.demo.yadi.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ca.isucorp.demo.yadi.dto.ReservationDTO;
import ca.isucorp.demo.yadi.model.Contact;
import ca.isucorp.demo.yadi.model.Place;
import ca.isucorp.demo.yadi.model.Reservation;
import ca.isucorp.demo.yadi.repository.ReservationRepository;

@Service
public class ReservationService {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	PlaceService placeService;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	
	public Page<Reservation> getReservationList(Pageable pageable) {
		return this.reservationRepository.findAll(pageable);
	}
	
	public Reservation getReservation(Long id) {
		return this.reservationRepository.findById(id).orElse(null);
	}

	public List<Reservation> getReservationByContact(Contact contact) {
		return this.reservationRepository.getReservationsByContact(contact);
	}

	public void removeReservationsByContact(Contact contact) {
		List<Reservation> reservations = this.getReservationByContact(contact);
		if (!reservations.isEmpty()) {
			this.reservationRepository.deleteInBatch(reservations);
		}
	}

	/**
	 * Create reservation for existing contact, if the contact doesn't exist, create one first
	 * @param reservationDto
	 * @return
	 */
	public Reservation createReservation(ReservationDTO reservationDto) {
		/* place is required field for a reservation */
		if (reservationDto.getPlace() == null) {
			throw new IllegalArgumentException();
		}
		
		Place place = this.placeService.getPlace(reservationDto.getPlace().getId());
		if (place == null) {
			throw new IllegalArgumentException();
		}

		/* if contact doesn't exist, create one */
		Contact contact = null;
		if (reservationDto.getContact().getId() == null) {
			contact = this.contactService.createContact(reservationDto.getContact());
		} else {
			contact = this.contactService.updateContact(reservationDto.getContact());
		}
		
		Reservation reservation = new Reservation(contact, place, LocalDate.parse(reservationDto.getVisitDateTime(), formatter), reservationDto.getDescription());
		reservation.setFavorite(reservationDto.isFavorite());
		this.reservationRepository.save(reservation);
		return reservation;
	}
	
	/**
	 * Update reservation, if the contact doesn't exist, create one first
	 * @param reservationDto
	 * @return
	 */
	public Reservation updateReservation(ReservationDTO reservationDto) {
		/* place is required field for a reservation */
		if (reservationDto.getPlace() == null) {
			throw new IllegalArgumentException();
		}
		
		Place place = this.placeService.getPlace(reservationDto.getPlace().getId());
		if (place == null) {
			throw new IllegalArgumentException();
		}
		
		Reservation reservation = reservationRepository.findById(reservationDto.getId())
				.map(r -> {
					/* if contact doesn't exist, create one */
					Contact contact = null;
					if (reservationDto.getContact().getId() == null) {
						contact = this.contactService.createContact(reservationDto.getContact());
					} else {
						contact = this.contactService.updateContact(reservationDto.getContact());
					}
					
					r.setContact(contact);
					r.setPlace(place);
					r.setFavorite(reservationDto.isFavorite());
					r.setVisitDateTime(LocalDate.parse(reservationDto.getVisitDateTime(), formatter));
					r.setDescription(reservationDto.getDescription());
					return r;
				})
				.orElseThrow(() -> new IllegalArgumentException());
		this.reservationRepository.save(reservation);
		return reservation;
	}
	
}

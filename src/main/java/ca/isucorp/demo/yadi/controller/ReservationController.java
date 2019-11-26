package ca.isucorp.demo.yadi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.isucorp.demo.yadi.dto.PageRequestDTO;
import ca.isucorp.demo.yadi.dto.ReservationDTO;
import ca.isucorp.demo.yadi.model.Reservation;
import ca.isucorp.demo.yadi.service.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/reservation/list")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Page<Reservation> list(@Valid PageRequestDTO pageRequestDto) {
		Pageable pageable = pageRequestDto.toPageable();
		return reservationService.getReservationList(pageable);
    }
	
	@GetMapping("/reservation/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Reservation get(@PathVariable Long id) {
		Reservation reservation = reservationService.getReservation(id);
		if (reservation == null) {
			throw new IllegalArgumentException();
		}
		return reservation;
    }
	
	
	@PostMapping("/reservation")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Reservation create(@Valid @RequestBody ReservationDTO reservationDto) {
		return reservationService.createReservation(reservationDto);
    }
	
	@PutMapping("/reservation/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Reservation update(@PathVariable Long id, @Valid @RequestBody ReservationDTO reservationDto) {
		reservationDto.setId(id);
		return reservationService.updateReservation(reservationDto);
    }
}

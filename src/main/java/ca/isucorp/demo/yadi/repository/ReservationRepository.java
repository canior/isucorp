package ca.isucorp.demo.yadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.isucorp.demo.yadi.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}

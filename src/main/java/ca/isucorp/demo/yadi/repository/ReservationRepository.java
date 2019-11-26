package ca.isucorp.demo.yadi.repository;

import ca.isucorp.demo.yadi.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import ca.isucorp.demo.yadi.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r INNER JOIN r.contact c WHERE c = :contact")
    List<Reservation> getReservationsByContact(Contact contact);
}

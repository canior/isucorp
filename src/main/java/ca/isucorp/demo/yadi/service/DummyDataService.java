package ca.isucorp.demo.yadi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.isucorp.demo.yadi.model.Place;
import ca.isucorp.demo.yadi.repository.ContactRepository;
import ca.isucorp.demo.yadi.repository.PlaceRepository;
import ca.isucorp.demo.yadi.repository.ReservationRepository;

@Service
public class DummyDataService {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PlaceRepository placeRepository;
	
	public void createDummyData() {
		/* create dummy place data */
		this.placeRepository.save(new Place("Dummy Place 1", "https://via.placeholder.com/150x150.png?text=DummyPlace_1", 1));
		this.placeRepository.save(new Place("Dummy Place 2", "https://via.placeholder.com/150x150.png?text=DummyPlace_2", 2));
		this.placeRepository.save(new Place("Dummy Place 3", "https://via.placeholder.com/150x150.png?text=DummyPlace_3", 3));
		this.placeRepository.save(new Place("Dummy Place 4", "https://via.placeholder.com/150x150.png?text=DummyPlace_4", 4));
		this.placeRepository.save(new Place("Dummy Place 5", "https://via.placeholder.com/150x150.png?text=DummyPlace_5", 5));
	}
}

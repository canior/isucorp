package ca.isucorp.demo.yadi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.isucorp.demo.yadi.model.Place;
import ca.isucorp.demo.yadi.repository.PlaceRepository;


@Service
public class PlaceService {
	
	@Autowired
	PlaceRepository placeRepository;
	
	public Place getPlace(Long id) {
		return this.placeRepository.findById(id).orElse(null);
	}
	
	public List<Place> getPlaceList() {
		return this.placeRepository.findAll();
	}
}

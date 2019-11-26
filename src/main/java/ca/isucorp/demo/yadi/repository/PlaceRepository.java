package ca.isucorp.demo.yadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.isucorp.demo.yadi.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long>{
}

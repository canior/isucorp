package ca.isucorp.demo.yadi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ca.isucorp.demo.yadi.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	@Query("SELECT c FROM Contact c WHERE c.name = :name")
	public Contact findOneContactByName(String name);
}

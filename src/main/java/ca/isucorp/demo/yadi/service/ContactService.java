package ca.isucorp.demo.yadi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ca.isucorp.demo.yadi.dto.ContactDTO;
import ca.isucorp.demo.yadi.model.Contact;
import ca.isucorp.demo.yadi.model.ContactType;
import ca.isucorp.demo.yadi.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository contactRepository;

	@Autowired
	ReservationService reservationService;
	
	public Page<Contact> getContactList(Pageable pageable) {
		return this.contactRepository.findAll(pageable);
	}
	
	public Contact getContact(Long id) {
		return this.contactRepository.findById(id).orElse(null);
	}
	
	public Contact createContact(ContactDTO contactDto) {
		Contact contact = new Contact(contactDto.getName(), Enum.valueOf(ContactType.class, contactDto.getContactType()), contactDto.getPhone(), contactDto.getBirthday());
		contactRepository.save(contact);
		return contact;
	}
	
	public Contact updateContact(ContactDTO contactDto) {
		Contact contact = contactRepository.findById(contactDto.getId())
				.map(c -> {
				c.setName(contactDto.getName());
				c.setPhone(contactDto.getPhone());
				c.setContactType(Enum.valueOf(ContactType.class, contactDto.getContactType()));
				c.setBirthday(contactDto.getBirthday());
				return c;
		}).orElseThrow(() -> new IllegalArgumentException());
		this.contactRepository.save(contact);
		return contact;
	}

	/**
	 * Delete contact deletes all related reservations
	 * @param id
	 */
	public void deleteContact(Long id) {
		Contact contact = this.contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		if (this.reservationService.getReservationByContact(contact) != null) {
			this.reservationService.removeReservationsByContact(contact);
		}
		this.contactRepository.deleteById(id);
	}
	
	public Contact findOneContactByName(String name) {
		return this.contactRepository.findOneContactByName(name);
	}
}

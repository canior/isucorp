package ca.isucorp.demo.yadi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.isucorp.demo.yadi.dto.ContactDTO;
import ca.isucorp.demo.yadi.dto.PageRequestDTO;
import ca.isucorp.demo.yadi.model.Contact;
import ca.isucorp.demo.yadi.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@GetMapping("/contact/list")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Page<Contact> list(@Valid PageRequestDTO pageRequestDto) {
		Pageable pageable = pageRequestDto.toPageable();
		return contactService.getContactList(pageable);
    }
	
	@GetMapping("/contact/search")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Contact search(@RequestParam String name) {
		Contact contact = contactService.findOneContactByName(name);
		if (contact == null) {
			throw new IllegalArgumentException();
		}
		return contact;
    }
	
	@GetMapping("/contact/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Contact get(@PathVariable Long id) {
		Contact contact = contactService.getContact(id);
		if (contact == null) {
			throw new IllegalArgumentException();
		}
		return contact;
    }
	
	
	@PostMapping("/contact")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Contact create(@Valid @RequestBody ContactDTO contactDto) {
		return contactService.createContact(contactDto);
    }
	
	@PutMapping("/contact/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Contact update(@PathVariable Long id, @Valid @RequestBody ContactDTO contactDto) {
		contactDto.setId(id);
		return contactService.updateContact(contactDto);
    }
	
	
	@DeleteMapping("/contact/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void delete(@PathVariable Long id) {
		contactService.deleteContact(id);
    }
	
}

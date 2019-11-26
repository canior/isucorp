package ca.isucorp.demo.yadi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity 
public class Contact {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private ContactType contactType;
	
	private String phone;
	
	private LocalDate birthday;
	
	public Contact() {
		
	}
	
	public Contact(String name, ContactType contactType, String phone, LocalDate birthday) {
		this.setName(name);
		this.setContactType(contactType);
		this.setPhone(phone);
		this.setBirthday(birthday);
	}
	
}

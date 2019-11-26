package ca.isucorp.demo.yadi.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactDTO {
	private Long id;
	
	@NotNull(message="Please input a valid name")
	@Size(min=3, message="Please input a name longer than 3")
	private String name;
	
	@Pattern(regexp="INDIVIDUAL|COMPANY", message="Please input INDIVIDUAL or COMPANY")
	private String contactType;
	
	private String phone;
	
	private LocalDate birthday;
}

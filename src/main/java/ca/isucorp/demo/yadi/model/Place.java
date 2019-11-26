package ca.isucorp.demo.yadi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity 
public class Place {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String placeImageUrl;
	
	private int ranking;
	
	public Place() {
		
	}
	
	public Place(String name, String placeImageUrl, int ranking) {
		super();
		this.name = name;
		this.placeImageUrl = placeImageUrl;
		this.ranking = ranking;
	}
}

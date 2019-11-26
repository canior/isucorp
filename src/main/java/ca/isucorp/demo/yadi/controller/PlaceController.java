package ca.isucorp.demo.yadi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.isucorp.demo.yadi.model.Place;
import ca.isucorp.demo.yadi.service.PlaceService;

@Controller
public class PlaceController {
	@Autowired
	PlaceService placeService;
	
	@GetMapping("/place/list")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<Place> list() {
		return placeService.getPlaceList();
    }
}

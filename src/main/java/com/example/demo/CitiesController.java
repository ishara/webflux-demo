package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Madhura Bhave
 */
@RestController
public class CitiesController {

	private CityRepository repository;

	public CitiesController(CityRepository repository) {
		this.repository = repository;
	}

//	@RequestMapping("/")
//	public Rendering index(Model model) {
////		model.addAttribute("cities", new ReactiveDataDriverContextVariable(all()));
//		return Rendering.view("index")
//                .modelAttribute("cities", new ReactiveDataDriverContextVariable(all()))
//                .build();
//	}

	@RequestMapping("/cities/{id}")
	public Mono<City> findById(@PathVariable String id) {
		return this.repository.findById(id);
	}

	@RequestMapping(value = "/cities")
	public Flux<Object> all() {
		return this.repository.findAll()
				.filter(c -> c.getCountry().equals("USA"))
				.map(city -> city);
	}
}

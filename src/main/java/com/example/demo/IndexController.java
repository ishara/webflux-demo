package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

@Controller
public class IndexController {

    private CityRepository repository;

    public IndexController(CityRepository repository) {
        this.repository = repository;
    }
    @RequestMapping("/")
    public Rendering index(Model model) {
//		model.addAttribute("cities", new ReactiveDataDriverContextVariable(all()));
        return Rendering.view("index")
                .modelAttribute("cities", new ReactiveDataDriverContextVariable(all()))
                .build();
    }
    public Flux<Object> all() {
        return this.repository.findAll()
                .filter(c -> c.getCountry().equals("USA"))
                .map(city -> city);
    }
}

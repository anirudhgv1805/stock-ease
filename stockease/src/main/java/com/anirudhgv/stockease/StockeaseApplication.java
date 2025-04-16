package com.anirudhgv.stockease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@SpringBootApplication
public class StockeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockeaseApplication.class, args);
	}

	@GetMapping("/api/test")
	public String checkBackend() {
		return "The backnd is available and working";
	}
	
}

package com.bm.denver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DenverApplication {

	@GetMapping("/hello")
	public String check() {
		return "8080 Hello World 1128denver";
	}

	@GetMapping("/")
	public String check8080() {
		return "8080 root ";
	}

	public static void main(String[] args) {
		SpringApplication.run(DenverApplication.class, args);
	}

}

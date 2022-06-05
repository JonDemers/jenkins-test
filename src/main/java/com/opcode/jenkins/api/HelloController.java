package com.opcode.jenkins.api;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Hello from Spring Boot v8 !!\n"
				+ "Date: " + new Date() + "\n";
	}

}
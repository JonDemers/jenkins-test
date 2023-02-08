package com.opcode.jenkins.api;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private final Date startTime = new Date();

	@GetMapping("/")
	public String index() {
		return "Hello from Spring Boot v9, StartTime: " + startTime;
	}

}
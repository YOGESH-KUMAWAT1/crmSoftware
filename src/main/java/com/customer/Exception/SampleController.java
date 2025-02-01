package com.customer.Exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/resource")
	public String getResource(@RequestParam String resourceId) {
		if ("notfound".equals(resourceId)) {
			throw new ResourceNotFoundException("Resource not found with ID: " + resourceId);
		}
		return "Resource found: " + resourceId;
	}

	@GetMapping("/input")
	public String handleInvalidInput(@RequestParam String input) {
		if ("invalid".equals(input)) {
			throw new InvalidInputException("Invalid input provided: " + input);
		}
		return "Input is valid: " + input;
	}
}

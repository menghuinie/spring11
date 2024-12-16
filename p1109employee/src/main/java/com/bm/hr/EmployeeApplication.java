package com.bm.hr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.bm.hr.model.EmployeeDTO;
import com.bm.hr.service.EmployeeService;

@SpringBootApplication
@RestController
public class EmployeeApplication implements CommandLineRunner {
	@Autowired 
	private EmployeeService employeeService;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}


	@GetMapping("/")
	public String check8080() {
		return "EmployeeApplication 8080";
	}


	@Override 
	public void run(String... args) throws Exception { 
		// Initialize database with default employees. 
		employeeService.saveEmployee(
			new EmployeeDTO( 11L,"firstName11", "lastName11", "email11@bm.com")	
		); 
		employeeService.saveEmployee(
			new EmployeeDTO( 22L,"firstName22", "lastName22", "email22@bm.com")
		); 

		System.out.println("Database initialized with default employees.");
		System.out.println("NOW at CommandLineRunner: after the application has started."); // Add your startup logic here 
	}
}

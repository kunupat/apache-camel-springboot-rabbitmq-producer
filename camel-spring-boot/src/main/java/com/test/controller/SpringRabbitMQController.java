package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Employee;
import com.test.services.EmployeeService;

@RestController
public class SpringRabbitMQController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee")
	public String createEmployee(@RequestBody Employee newEmployee) {

		employeeService.createEmployee(newEmployee);

		return "Saved Employee: " + newEmployee;
	}

	@GetMapping("/employee/{empId}")
	String getEmployeeById(@PathVariable("empId") String empId) {
		Employee employee = new Employee();
		employee.setEmpId(new Integer(empId));
		return employeeService.getEmployeeById(employee);
	}
}
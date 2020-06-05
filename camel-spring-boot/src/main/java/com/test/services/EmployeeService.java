package com.test.services;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.model.Employee;

@Component
public class EmployeeService {

	@Produce(value = "direct:restEndpoint")
	private ProducerTemplate restProducerTemplate;


	@Value(value = "${employeeServiceURL}")
	String employeeServiceURL;
	
	public void createEmployee(Employee employee) {
		restProducerTemplate.asyncSendBody(restProducerTemplate.getDefaultEndpoint(), employee);
	}

	public String getEmployeeById(Employee employee) {
		RestTemplate restTemplate = new RestTemplate();
		
		String EmployeeServiceURL = employeeServiceURL + "/employee/" + employee.getEmpId();
  	
		ResponseEntity<String> response = restTemplate.getForEntity(EmployeeServiceURL, String.class);
		
		return response.getBody();
	
	}
}

package com.test.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import com.test.model.Employee;

@Component
public class RabbitMQRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);

		from("direct:restEndpoint")
		.routeId("rabbitMQRoute")
		.marshal(jsonDataFormat)
		.to("rabbitmq://localhost:5672/TEST-QUEUE.exchange?queue=Checkin.queue&autoDelete=false")
		.log(LoggingLevel.INFO, "Sent to RabbitMQ... ")
		.end();
	}
}
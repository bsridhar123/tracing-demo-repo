package com.demo.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class MessagePrinter {

	private static final Logger logger = LoggerFactory.getLogger(MessagePrinter.class);
	
	
	public String printConsole(Message<?> message){
		logger.info("Received:" + message.getPayload().toString());
		return message.getPayload().toString();
	}
	
}

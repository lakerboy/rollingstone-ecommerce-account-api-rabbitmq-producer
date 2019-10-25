package com.rollingstone.listners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountMessageSender {

	private static final Logger log = LoggerFactory.getLogger(AccountMessageSender.class);
	
	public void sendMessage(RabbitTemplate rabbitTemplate, String accountExchange, String accountRoutingKey, Object accountData	) {
		log.info("Sending Message to the account Queue using accountRoutingKey{}, Message {}", accountRoutingKey, accountData);
		
		rabbitTemplate.convertAndSend(accountExchange, accountRoutingKey, accountData);
		
		log.info("Teh Account Message was sent");
	}
	
}

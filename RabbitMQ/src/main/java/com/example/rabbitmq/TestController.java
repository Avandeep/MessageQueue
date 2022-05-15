package com.example.rabbitmq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
/*	@GetMapping("/test/{name}")
	public String testAPI(@PathVariable("name") String name)
	{
		Person p=new Person(1L,name);
		rabbitTemplate.convertAndSend("Mobile",p);
		rabbitTemplate.convertAndSend("Direct-Exchange","mobile",p);
		rabbitTemplate.convertAndSend("Fanout-Exchange","",p);
		rabbitTemplate.convertAndSend("Topic-Exchange","tv.mobile.ac",p);
		//support only byte, string, serialiazable objects
		return "success";
		
	}*/
	
	@GetMapping("/test/{name}")
	public String testAPI(@PathVariable("name") String name) throws IOException
	{
		Person p=new Person(1L,name);
		
		//convert person object 'p' into bytearray
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ObjectOutput oo=new ObjectOutputStream(baos);
		oo.writeObject(p);
		oo.flush();
		oo.close();
		
		byte[] byteMessage= baos.toByteArray();
		baos.close();
		
		//create Message object to pass byte array value
		
		Message message=MessageBuilder.withBody(byteMessage)
				.setHeader("item1", "mobile")
				.setHeader("item2", "television").
				build();
		
		rabbitTemplate.send("Headers-Exchange","",message);
		
		return "success";
	}
	

}

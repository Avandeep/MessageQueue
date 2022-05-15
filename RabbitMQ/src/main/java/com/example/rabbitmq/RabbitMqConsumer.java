package com.example.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {
	
	/*@RabbitListener(queues="Mobile")
	public void getMessage(Person p)
	{
		System.out.println(p.getName());
	}
*/
	
	@RabbitListener(queues="Mobile")
	public void getMessage(byte[] message) throws ClassNotFoundException, IOException
	{
		ByteArrayInputStream bais=new ByteArrayInputStream(message);
		ObjectInput oi=new ObjectInputStream(bais);
		Person p=(Person) oi.readObject();
		oi.close();
		bais.close();
		System.out.println(p.getName());
	}
}

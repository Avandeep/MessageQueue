package com.queuepublisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//how to publish the message to queue

public class Publisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory = new ConnectionFactory();
		//to publish the message on queue, we have to create object ConnectionFactory class, connection and general
		//interface to the client class
		
		Connection connection=factory.newConnection();
		
		Channel channel=connection.createChannel();
		//using the channel object, leave the message to the queue.
		
		//String msg="First Message from RabbitMQ";
		String messages[]= {"First","Second","Third","Fourth"};
		
		for(String message:messages)
		{
			channel.basicPublish("", "Queue-1", null, message.getBytes());	
		}
		
		
		
		channel.close();
		connection.close();
		
	}

}

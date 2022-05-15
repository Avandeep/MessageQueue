package com.queuepublisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

//how to consume the message from queue

public class Consumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		
		DeliverCallback delivercallback=(consumerTag,delivery)->
		{
			String message=new String(delivery.getBody());
			System.out.println("Message Received:"+message);
		};
		
		channel.basicConsume("Queue-1", true, delivercallback,consumerTag->{});
		//We didn't close it, so it will continuously running and listening to queue
		//ack=true; so it also acknowlegded the queue regarding message receiving

	}

}

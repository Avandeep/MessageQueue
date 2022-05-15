package com.fanoutexchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//this is publish subscriber

public class FanoutPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		
		String message="This is mobile ac subscriber";
		
		channel.basicPublish("Fanout-Exchange", "Mobile", null, message.getBytes());
		
		channel.close();
		connection.close();
	}

}

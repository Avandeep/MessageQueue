package com.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//publishing message to direct exchange

public class DirectPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		
		String message="This is mobile";
		
		channel.basicPublish("Direct-Exchange", "mobile", null, message.getBytes());
		
		channel.close();
		connection.close();
		
	}

}

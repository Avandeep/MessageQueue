package com.queuepublisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

// how to publish json messages to queue

public class RealTimePublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		
		JSONObject json=new JSONObject();
		json.put("from date", "1-Jan-2021");
		json.put("to date", "31-Dec-2021");
		json.put("email", "xyz@gmail.com");
		json.put("query", "select * from data");
		
		channel.basicPublish("", "Queue-1", null,json.toString().getBytes());
		
		channel.close();
		connection.close();
		
		
	}

}

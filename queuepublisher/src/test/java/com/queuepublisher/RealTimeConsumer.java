package com.queuepublisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

// how to publish json messages to queue

public class RealTimeConsumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		
		DeliverCallback delivercallback=(consumerTag,delivery)->
		{
			String message=new String(delivery.getBody());
			JSONObject json=new JSONObject();
			Object object = json.get(message);
			System.out.println("Messages...."+message);
			System.out.println("Message Received:"+object);
			System.out.println("Message Received:"+object.toString());
		};
		
		channel.basicConsume("Queue-1", true, delivercallback,consumerTag->{});
		
//	channel.close();
	//connection.close();
		
		
	}

}

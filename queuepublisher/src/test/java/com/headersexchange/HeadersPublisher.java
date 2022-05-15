package com.headersexchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HeadersPublisher {

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory=new ConnectionFactory();
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		
		String message="Message from mobile and television";
		
		Map<String, Object> headers=new HashMap<String, Object>();
		headers.put("item1", "mobile");
		headers.put("item2", "television");
		
		BasicProperties br=new BasicProperties();
		br.builder().headers(headers).build();
		
		channel.basicPublish("Headers-Exchange", "", br, message.getBytes());
		
		channel.close();
		connection.close();
	}

}

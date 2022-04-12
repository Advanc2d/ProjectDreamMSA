package com.dream.orderService.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.dream.orderService.domain.SendMessage;

import lombok.extern.slf4j.Slf4j;

//KafkaProducer
@EnableKafka
@Configuration
@Slf4j
public class KafkaOrderService {
	@Value("${kafka.server_endpoint}")
	   private String kafkaServerEndpoint;
	   
	   @Value("${kafka.topic_name}")
	   private String kafkaTopicName;
	   
	   @Bean
	   public ProducerFactory<String, SendMessage> producerFactory(){ //접속하고자 하는 정보 topic
	      Map<String, Object> configProps = new HashMap<>();
	      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
	      log.info("Order kafka Producer TopicName : " + kafkaTopicName);   
	      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	         
	      return new DefaultKafkaProducerFactory<>(configProps);
	   }
	   
	   @Bean
	   public KafkaTemplate<String, SendMessage> kafkaTemplate(){
	       return new KafkaTemplate<>(producerFactory());
	   }
}

package com.dream.manageservice.config;

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

import com.dream.manageservice.dto.StatusMessage;

import lombok.extern.slf4j.Slf4j;

@EnableKafka
@Configuration
@Slf4j
public class KafkaManageConfig {
	
	@Value("${kafka.server_endpoint}")
	   private String kafkaServerEndpoint;
	   
	   @Value("${kafka.topic_name}")
	   private String kafkTopicName;
	   
	   @Bean
	   public ProducerFactory<String, StatusMessage> producerFactory(){ //접속하고자 하는 정보 topic
	      Map<String, Object> configProps = new HashMap<>();
	      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
	      log.info("Manage Kafka Producer Topic Name : " + kafkTopicName);    
	      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	         
	      return new DefaultKafkaProducerFactory<>(configProps);
	   }
	   
	   @Bean
	   public KafkaTemplate<String, StatusMessage> kafkaTemplate(){
	       return new KafkaTemplate<>(producerFactory());
	   }
}

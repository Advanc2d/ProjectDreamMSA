package com.dream.mainservice.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.dream.mainservice.dto.Message;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import lombok.extern.slf4j.Slf4j;

//KafkaProducer
@EnableKafka
@Configuration
@Slf4j
public class KafkaMainService {
   
   @Value("${kafka.server_endpoint}")
   private String kafkaServerEndpoint;
   
   @Value("${kafka.topic_name}")
   private String kafkTopicName;
   
   @Bean
   public ProducerFactory<String, Message> producerFactory(){ //접속하고자 하는 정보 topic
      Map<String, Object> configProps = new HashMap<>();
      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
      log.info("------------------토픽네임" + kafkTopicName);      
      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
         
      return new DefaultKafkaProducerFactory<>(configProps);
   }
   
   @Bean
   public KafkaTemplate<String, Message> kafkaTemplate(){
       return new KafkaTemplate<>(producerFactory());
   }
}
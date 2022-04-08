package com.dream.orderService.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.dream.orderService.domain.Message;

import lombok.extern.slf4j.Slf4j;

@EnableKafka
@Configuration
@Slf4j
public class KafkaOrderConfig {
	private Message	vo;

   @Value("${kafka.server_endpoint}")
   private String kafkaServerEndpoint;

   @Value("${kafka.topic_name}")
   private String kafkTopicName;
   
   @Value(value = "${kafka.group_id}")
   private String kafkaGroupId;

   @Bean
   public ConsumerFactory<String, String> consumerFactory() {
      Map<String, Object> map = new HashMap<>();
      map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerEndpoint);
      log.info("앤드포인트" + kafkaServerEndpoint);
      log.info("------------------토픽네임" + kafkTopicName);
      log.info("------------------그룹아이디" + kafkaGroupId);
      map.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
      map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
      map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

      return new DefaultKafkaConsumerFactory<>(map);
   }
   
   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListner(){
      
      ConcurrentKafkaListenerContainerFactory<String, String> obj = new ConcurrentKafkaListenerContainerFactory<>();
      obj.setConsumerFactory(consumerFactory());
      return obj;
   }
}
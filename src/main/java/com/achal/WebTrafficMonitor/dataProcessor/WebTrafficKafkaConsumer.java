package com.achal.WebTrafficMonitor.dataProcessor;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class WebTrafficKafkaConsumer
{

    public static void main(String[] args)
    {
        // Kafka consumer configuration
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "web-traffic-consumer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // Create the KafkaConsumer instance
        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(properties);

        // Subscribe to the Kafka topic(s)
        consumer.subscribe(Collections.singletonList("web-traffic-topic"));

        // Start consuming messages
        while (true)
        {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records)
            {
                String key = record.key();
                String value = record.value();
                System.out.printf("Received message: [Key: %s, Value: %s]%n", key, value);

                // Process the received event (you can call the DataProcessorService here)
                processEvent(value);
            }
        }
    }

    private static void processEvent(String eventData)
    {
        // This is where I will implement further processing based on the received event data
    }
}

package com.achal.WebTrafficMonitor.dataCollector;

import java.util.Random;

public class DataCollectorApplication
{

    public static void main(String[] args)
    {
        // Initialize the DataGenerator and start simulating website traffic
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.startGeneratingData();
    }
}

class DataGenerator {
    private final Random random = new Random();

    public void startGeneratingData() {
        // Simulate website traffic by generating random events
        while (true) {
            // Generate random user ID, page URL, and event type
            String userId = generateRandomUserId();
            String pageUrl = generateRandomPageUrl();
            String eventType = generateRandomEventType();

            // Process the event (you can send it to Kafka here)
            processEvent(userId, pageUrl, eventType);

            // Wait for a short interval before generating the next event
            sleepForInterval();
        }
    }

    private String generateRandomUserId() {
        return "User_" + random.nextInt(1000);
    }

    private String generateRandomPageUrl() {
        String[] urls = {
                "/home", "/about", "/contact", "/products", "/services"
                // Add more page URLs as needed
        };
        return urls[random.nextInt(urls.length)];
    }

    private String generateRandomEventType() {
        String[] eventTypes = {
                "click", "view", "hover", "submit", "purchase"
                // Add more event types as needed
        };
        return eventTypes[random.nextInt(eventTypes.length)];
    }

    private void processEvent(String userId, String pageUrl, String eventType) {
        // This is where I will perform additional processing or send the event to Kafka
        System.out.printf("Event: [User: %s, Page: %s, Event Type: %s]%n", userId, pageUrl, eventType);
    }

    private void sleepForInterval() {
        try
        {
            // Sleep for a random interval between 500ms to 2000ms
            Thread.sleep(500 + random.nextInt(1500));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace(); //Will replace this eventually
        }
    }
}

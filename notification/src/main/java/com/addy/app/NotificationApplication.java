package com.addy.app;


import com.addy.app.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {
        "com.addy.app",
        "com.addy.app.config"
})
@EnableDiscoveryClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
       SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(NotificationMessageProducer producer, NotificationConfig notificationConfig){
        return args -> {
            producer.publish( new Person("Addy", 12), notificationConfig.getInternalExchange(), notificationConfig.getInternalNotificationRoutingKey());
        };
    }
    record Person (String name, int age){};
}
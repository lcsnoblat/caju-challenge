package com.authorization.cajuchallenge.repository.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoQueryConfig {

    @Bean
    public MongoClient queryMongoClient() {
        return MongoClients.create("mongodb://root:example@localhost:27018");
    }

    @Bean
    public MongoTemplate queryMongoTemplate() {
        return new MongoTemplate(queryMongoClient(), "query_db");
    }
}
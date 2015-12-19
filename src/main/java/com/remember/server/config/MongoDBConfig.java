package com.remember.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by NerdHerd on 2015. 12. 19..
 */

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {
		"com.remember.server.repository"
})
@EnableTransactionManagement
public class MongoDBConfig {
}

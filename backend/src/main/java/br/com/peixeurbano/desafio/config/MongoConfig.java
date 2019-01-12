package br.com.peixeurbano.desafio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
  @Override
  public MongoClient mongoClient() {
    return new MongoClient();
  }

  @Override
  protected String getDatabaseName() {
    return "deal";
  }
}

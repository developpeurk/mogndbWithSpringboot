package com.lambarki.mongodbdemo;

import com.lambarki.mongodbdemo.product.Product;
import com.lambarki.mongodbdemo.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongodbdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbdemoApplication.class, args);
	}

	//@Bean
	public CommandLineRunner commandLineRunner(
			ProductRepository productRepository
	){
		return args -> {
			var product = Product.builder()
					.name("iPhone")
					.description("Smart Phone")
					.build();
			productRepository.insert(product);
		};
	}
}

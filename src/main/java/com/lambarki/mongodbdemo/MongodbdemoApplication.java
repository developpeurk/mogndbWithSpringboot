package com.lambarki.mongodbdemo;

import com.lambarki.mongodbdemo.category.Category;
import com.lambarki.mongodbdemo.category.CategoryRepository;
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
			CategoryRepository categoryRepository
	){
		return args -> {
			var category = Category.builder()
					.name("cat 1")
					.description("cat 1")
					.build();
			var category2 = Category.builder()
					.name("cat 2")
					.description("cat 2")
					.build();
			categoryRepository.insert(category);
			categoryRepository.insert(category2);

		};
	}
}

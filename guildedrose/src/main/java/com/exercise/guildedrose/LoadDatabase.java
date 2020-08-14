package com.exercise.guildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(ItemRepository itemRepository) {

		return args -> {

			itemRepository.save(new Item("Exercise Band", "Build leg strength", 10));
			itemRepository.save(new Item("Grooming Brush", "Use on pet", 10));
			itemRepository.save(new Item("Grill Mat", "Use for BBQ", 20));
			itemRepository.save(new Item("Veggie Spiralizer", "Cut your vegetables", 30));
			itemRepository.save(new Item("Pet Nail Clippers", "Cut pet nails", 15));
			itemRepository.save(new Item("Yoga Mat", "Cool perk", 10));
			itemRepository.save(new Item("Bento Boxes", "Good for packing meals", 20));
			itemRepository.save(new Item("Steel Straw", "Save the turtles", 10));
			itemRepository.save(new Item("Steel Tumblers", "Cool mug", 15));
			itemRepository.save(new Item("Muscle Roller", "Recover", 15));

			itemRepository.findAll().forEach(item -> log.info("Preloaded " + item));

		};
	}

}

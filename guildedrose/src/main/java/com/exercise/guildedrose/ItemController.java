package com.exercise.guildedrose;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
	
	private final ItemRepository repository;
	
	private final ItemModelAssembler assembler;
	
	ItemController(ItemRepository repository, ItemModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	// Aggregate root
	
	@GetMapping("/items")
	CollectionModel<EntityModel<Item>> all() {
		List<EntityModel<Item>> items = repository.findAll().stream()
	      .map(assembler::toModel)
	      .collect(Collectors.toList());
		return CollectionModel.of(items,
			linkTo(methodOn(ItemController.class).all()).withSelfRel());
	}
	
//	@PostMapping("/items")
//	ResponseEntity<?> newItem(@RequestBody Item newItem) {
//		EntityModel<Item> entityModel = assembler.toModel(repository.save(newItem));	
//		return ResponseEntity
//			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//			.body(entityModel);
//	}
	
	// Single item
	
	@GetMapping("/items/{id}")
	EntityModel<Item> one(@PathVariable Long id) {
		Item item = repository.findById(id)
			.orElseThrow(() -> new ItemNotFoundException(id));
		item.setViews(item.getViews() + 1);
		if (item.getViews() > 10 && System.currentTimeMillis() - item.getStart() < 3600000) {
			item.setPrice((int) (item.getPrice() * 1.1));
		}
		if (item.getViews() > 1 && System.currentTimeMillis() - item.getStart() > 3600000) {
			item.setStart(System.currentTimeMillis());
			item.setPrice((int) (item.getPrice() / 1.1));
			item.setViews(1);
		}
		return assembler.toModel(item);
	}
	
//	@PutMapping("/items/{id}")
//	ResponseEntity<?> replaceItem(@RequestBody Item newItem, @PathVariable Long id) {
//		Item updatedItem = repository.findById(id)
//			.map(item -> {
//				item.setName(newItem.getName());
//				item.setDescription(newItem.getDescription());
//				item.setPrice(newItem.getPrice());
//				return repository.save(item);
//			})
//			.orElseGet(() -> {
//				newItem.setId(id);
//				return repository.save(newItem);
//			});
//		EntityModel<Item> entityModel = assembler.toModel(updatedItem);
//		return ResponseEntity
//			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//			.body(entityModel);
//	}
	
	@GetMapping("/items/{id}/delete")
	ResponseEntity<?> deleteItem(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}

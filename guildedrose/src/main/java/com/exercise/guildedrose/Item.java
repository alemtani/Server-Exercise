package com.exercise.guildedrose;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
	
	private @Id @GeneratedValue Long id;
	private Long start = System.currentTimeMillis();
	private String name;
	private String description;
	private int price;
	private int views;
	
	Item() {}
	
	Item(String name, String description, int price) {
		this.start = System.currentTimeMillis();
		this.name = name;
		this.description = description;
		this.price = price;
		this.views = 0;
	}
	
	public Long getStart() {
		return this.start;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public int getPrice() {
		return this.price;
	}
	
	public int getViews() {
		return this.views;
	}
	
	public void setStart(Long start) {
		this.start = start;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setViews(int views) {
		this.views = views;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Item)) return false;
		Item item = (Item) o;
		return Objects.equals(this.id, item.id) &&
				Objects.equals(this.name, item.name) &&
				Objects.equals(this.description, item.description) &&
				this.price == item.price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.description, this.price);
	}
	
	@Override
	public String toString() {
		return "Item{" +
				"id=" + this.id +
				", name='" + this.name + '\'' +
				", description='" + this.description + '\'' +
				", price=" + this.price +
				"}";
	}

}

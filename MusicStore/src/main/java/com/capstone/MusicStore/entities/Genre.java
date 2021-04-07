package com.capstone.MusicStore.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


import lombok.Data;

@Entity
@Data
public class Genre {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	private String name;
	
	@ManyToMany
	private Set<Category> categories;
	
	public Genre() {}
	
	public Genre(String name, Set<Category> categories) {
		this.name = name;
		this.categories = categories;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Iterable<Category> getCategories() {
		return this.categories;
	}

	public void setCategory(Set<Category> categories) {
		this.categories = categories;
	}

}

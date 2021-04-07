package com.capstone.MusicStore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.capstone.MusicStore.entities.Genre;

public interface GenreRepository extends CrudRepository<Genre, Integer>{

}

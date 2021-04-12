package com.capstone.MusicStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.MusicStore.entities.Genre;
import com.capstone.MusicStore.repositories.GenreRepository;

@Service
public class GenreService {
	 @Autowired
	 GenreRepository genreRepository;
	 
	 public Genre getGenre(int genreId) {
		 return genreRepository.findById(genreId).orElse(null);
	 }
	 
	 public Iterable<Genre> GetAllGenre() {
			return genreRepository.findAll();
		}
	 
	 public Genre saveGenre(Genre genre) {
		 return genreRepository.save(genre);
	 }
	 
	 public void deleteGenre(int genreId) {
		 genreRepository.delete(getGenre(genreId));
	 }
	
}

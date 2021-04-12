package com.capstone.MusicStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.MusicStore.entities.Genre;
import com.capstone.MusicStore.services.GenreService;

@RestController
public class GenreController {
	@Autowired
	GenreService genreService;
	
	@GetMapping("/genre")
	@CrossOrigin
	public @ResponseBody Iterable<Genre> getAllGenre() {
        // This returns a JSON or XML with the Feedbacks
		System.out.println("Get hit");
        return genreService.GetAllGenre();
    }
	
	@PostMapping("/genre")
	@CrossOrigin
	public Genre saveCategory(@RequestBody Genre genre) {
		System.out.println("Post hit");
		return genreService.saveGenre(genre);
	}
	
	@PostMapping("/deleteGenre")
	@CrossOrigin
	public void deleteGenre(@RequestBody int genreId) {
		System.out.println("Post hit");
		genreService.deleteGenre(genreId);;
	}
	
	@RequestMapping(value="/genreView", method= RequestMethod.GET)
	public ModelAndView getCartView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("genreAndCategory/genre");
		return mav;
	}
}

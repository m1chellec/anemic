package br.com.ractecnologia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ractecnologia.dto.MovieDTO;
import br.com.ractecnologia.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	public MovieController() {
		super();
	}
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> findAll(){
		return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable("id") Long id){
		return new ResponseEntity<>(movieService.findById(id) ,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody MovieDTO movieDTO){
		movieService.create(movieDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody MovieDTO movieDTO){
		movieService.update(movieDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		movieService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

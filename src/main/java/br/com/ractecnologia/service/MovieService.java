package br.com.ractecnologia.service;

import java.util.List;

import br.com.ractecnologia.dto.MovieDTO;

public interface MovieService {

	List<MovieDTO> findAll();

	MovieDTO findById(Long id);

	void create(MovieDTO movieDTO);

	void update(MovieDTO movieDTO);

	void delete(Long id);

}

package br.com.ractecnologia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ractecnologia.dto.MovieDTO;
import br.com.ractecnologia.entity.Movie;
import br.com.ractecnologia.repository.MovieRepository;

@Service
public class MovieServiceImpl implements  MovieService{
	
	@Autowired
	private MovieRepository movieRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<MovieDTO> findAll() {
		List<Movie> list = movieRepository.findAll();
		return list.stream()
		          .map(obj -> modelMapper.map(obj, MovieDTO.class))
		          .collect(Collectors.toList());
	}

	@Override
	public MovieDTO findById(Long id) {
		Movie movie = movieRepository.findOne(id);
		MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
		return movieDTO;
	}

	@Override
	public void create(MovieDTO movieDTO) {
		Movie movie = modelMapper.map(movieDTO, Movie.class);
		movieRepository.save(movie);
	}

	@Override
	public void update(MovieDTO movieDTO) {
		Movie movie = modelMapper.map(movieDTO, Movie.class);
		movieRepository.save(movie);
	}

	@Override
	public void delete(Long id) {
		movieRepository.delete(id);
	}

}

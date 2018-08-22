package br.com.ractecnologia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ractecnologia.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}

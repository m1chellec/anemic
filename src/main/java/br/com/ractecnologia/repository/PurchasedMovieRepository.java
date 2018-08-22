package br.com.ractecnologia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ractecnologia.entity.PurchasedMovie;

public interface PurchasedMovieRepository extends JpaRepository<PurchasedMovie, Long> {


}

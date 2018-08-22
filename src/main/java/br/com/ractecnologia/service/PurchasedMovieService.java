package br.com.ractecnologia.service;

import br.com.ractecnologia.entity.Customer;
import br.com.ractecnologia.entity.Movie;

public interface PurchasedMovieService {

	void purchaseMovie(Customer customer, Movie movie);

}

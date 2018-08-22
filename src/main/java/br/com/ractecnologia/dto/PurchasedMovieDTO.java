package br.com.ractecnologia.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the purchased_movie database table.
 * 
 */
@Getter
@Setter
@NoArgsConstructor
public class PurchasedMovieDTO  {


	private String id;

	private Date expirationDate;

	private BigDecimal price;

	private Date purchaseDate;

	private MovieDTO movie;

	

}
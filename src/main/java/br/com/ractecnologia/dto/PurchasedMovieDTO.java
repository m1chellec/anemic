package br.com.ractecnologia.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ractecnologia.serialize.CustomDateSerializer;
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
public class PurchasedMovieDTO {

	private String id;

	@JsonSerialize(using = CustomDateSerializer.class)
	private Date expirationDate;

	private BigDecimal price;

	@JsonSerialize(using = CustomDateSerializer.class)
	private Date purchaseDate;

	private MovieDTO movie;

}
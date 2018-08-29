package br.com.ractecnologia.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ractecnologia.entity.CustomerStatus;
import br.com.ractecnologia.serialize.CustomerStatusNameDeserialize;
import br.com.ractecnologia.serialize.CustomDateDeserializer;
import br.com.ractecnologia.serialize.CustomDateSerializer;
import br.com.ractecnologia.serialize.MoneyDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the customer database table.
 * 
 */

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO extends CustomerCreateDTO {

	@JsonDeserialize(using = MoneyDeserializer.class)
	private BigDecimal moneySpent;

	// @JsonSerialize(using=CustomerStatusSerializer.class) //Cliente recebe os
	// dados
	@JsonDeserialize(using = CustomerStatusNameDeserialize.class) // cliente Envia os da
	private CustomerStatus status;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	// @DateTimeFormat(pattern="dd/MM/yyyy")
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date statusExpirationDate;

	private List<PurchasedMovieDTO> purchasedMovies;

}
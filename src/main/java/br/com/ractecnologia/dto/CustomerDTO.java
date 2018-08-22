package br.com.ractecnologia.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.ractecnologia.entity.CustomerStatus;
import br.com.ractecnologia.serialize.CustomerStatusNameDeserialize;
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
public class CustomerDTO extends CustomerCreateDTO  {
	
	@JsonDeserialize(using=MoneyDeserializer.class)
	private BigDecimal moneySpent;
	
//	@JsonSerialize(using=CustomerStatusSerializer.class) //Recebe os dados
	@JsonDeserialize(using = CustomerStatusNameDeserialize.class) //Envia os da
	private CustomerStatus status;

	private Date statusExpirationDate;

	private List<PurchasedMovieDTO> purchasedMovies;

	

}
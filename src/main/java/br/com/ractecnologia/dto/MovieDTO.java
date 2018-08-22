package br.com.ractecnologia.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.ractecnologia.entity.LicensingModel;
import br.com.ractecnologia.serialize.LicensingModelNameDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the movie database table.
 * 
 */

@Getter
@Setter
@NoArgsConstructor
public class MovieDTO {
	
	private Long id;
	
	@NotNull
	@JsonDeserialize(using = LicensingModelNameDeserialize.class)
	private LicensingModel licensingModel;

	@NotEmpty
	@Size(min=3)
	private String name;

}
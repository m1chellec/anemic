package br.com.ractecnologia.dto;

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

	@JsonDeserialize(using = LicensingModelNameDeserialize.class)
	private LicensingModel licensingModel;

	private String name;

}
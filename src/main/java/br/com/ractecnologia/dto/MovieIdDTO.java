package br.com.ractecnologia.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.ractecnologia.serialize.CustomDateDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieIdDTO {

	private Long id;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	//@DateTimeFormat(pattern="dd/MM/yyyy")
	@JsonDeserialize(using=CustomDateDeserializer.class)
	private Date dateTest;
}

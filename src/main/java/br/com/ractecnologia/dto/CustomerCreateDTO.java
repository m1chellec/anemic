package br.com.ractecnologia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerCreateDTO {
	
	private Long id;

	private String name;

	private String email;

}

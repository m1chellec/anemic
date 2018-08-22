package br.com.ractecnologia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import br.com.ractecnologia.converter.LicensingModelConverter;
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
@Entity
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
public class Movie implements Serializable {

	private static final long serialVersionUID = 5631609864681000999L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@Convert(converter = LicensingModelConverter.class)
	@Column(name="licensing_model")
	private LicensingModel licensingModel;

	private String name;

}
package br.com.ractecnologia.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the purchased_movie database table.
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="purchased_movie")
@NamedQuery(name="PurchasedMovie.findAll", query="SELECT p FROM PurchasedMovie p")
public class PurchasedMovie implements Serializable {

	private static final long serialVersionUID = -4625471883915422967L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date expirationDate;

	private BigDecimal price;

	@Temporal(TemporalType.DATE)
	@Column(name="purchase_date")
	private Date purchaseDate;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="id_customer")
	private Customer customer;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="id_movie")
	private Movie movie;

	

}
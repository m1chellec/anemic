package br.com.ractecnologia.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ractecnologia.converter.CustomerStatusConverter;
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
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {

	private static final long serialVersionUID = -6486366280317074597L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String name;

	@Column(name="money_spent")
	private BigDecimal moneySpent = BigDecimal.ZERO;

	@Convert(converter = CustomerStatusConverter.class)
	private CustomerStatus status;

	@Temporal(TemporalType.DATE)
	@Column(name="status_expiration_date")
	private Date statusExpirationDate;

	@OneToMany(mappedBy="customer")
	private List<PurchasedMovie> purchasedMovies = new ArrayList<>();

	public PurchasedMovie addPurchasedMovy(PurchasedMovie purchasedMovy) {
		purchasedMovies.add(purchasedMovy);
		purchasedMovy.setCustomer(this);
		return purchasedMovy;
	}

	public PurchasedMovie removePurchasedMovy(PurchasedMovie purchasedMovy) {
		purchasedMovies.remove(purchasedMovy);
		purchasedMovy.setCustomer(null);
		return purchasedMovy;
	}

}
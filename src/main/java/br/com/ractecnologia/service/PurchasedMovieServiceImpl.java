package br.com.ractecnologia.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ractecnologia.entity.Customer;
import br.com.ractecnologia.entity.CustomerStatus;
import br.com.ractecnologia.entity.LicensingModel;
import br.com.ractecnologia.entity.Movie;
import br.com.ractecnologia.entity.PurchasedMovie;
import br.com.ractecnologia.repository.CustomerRepository;
import br.com.ractecnologia.repository.PurchasedMovieRepository;

@Service
public class PurchasedMovieServiceImpl implements PurchasedMovieService {

	@Autowired
	private PurchasedMovieRepository purchasedMovieRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void purchaseMovie(Customer customer, Movie movie) {
		Date expirationDate = getExpirationDate(movie.getLicensingModel());
		
		BigDecimal price = calculatePrice(customer.getStatus(), customer.getStatusExpirationDate(),
				movie.getLicensingModel());
		PurchasedMovie purchasedMovie = new PurchasedMovie();
		purchasedMovie.setCustomer(customer);
		purchasedMovie.setMovie(movie);
		purchasedMovie.setExpirationDate(expirationDate);
		purchasedMovie.setPrice(price);
		purchasedMovie.setPurchaseDate(new Date());

		purchasedMovieRepository.save(purchasedMovie);
		customer.addPurchasedMovy(purchasedMovie);

		customer.setMoneySpent(customer.getMoneySpent().add(price));
		customerRepository.save(customer);
	}

	private BigDecimal calculatePrice(CustomerStatus status, Date statusExpirationDate, LicensingModel licensingModel) {
		BigDecimal price;
		switch (licensingModel) {
		case TWO_DAYS:
			price = new BigDecimal(4);
			break;

		case LIFE_LONG:
			price = new BigDecimal(8);
			break;

		default:
			throw new IllegalArgumentException();
		}

		if (status == CustomerStatus.ADVANCED
				&& (statusExpirationDate == null || statusExpirationDate.after(new Date()))) {
			price = price.multiply(new BigDecimal(0.75));
		}

		return price;
	}
	
	private Date getExpirationDate(LicensingModel licensingModel) {
		Date result;

         switch (licensingModel)
         {
             case TWO_DAYS:
            	 Calendar calendar = Calendar.getInstance();
            	 calendar.add(Calendar.DAY_OF_MONTH, 2);
                 result = calendar.getTime();
                 break;

             case LIFE_LONG:
                 result = null;
                 break;

             default:
            	 throw new IllegalArgumentException();
         }

		return result;
	}

}

package br.com.ractecnologia.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ractecnologia.dto.CustomerCreateDTO;
import br.com.ractecnologia.dto.CustomerDTO;
import br.com.ractecnologia.entity.Customer;
import br.com.ractecnologia.entity.CustomerStatus;
import br.com.ractecnologia.entity.Movie;
import br.com.ractecnologia.entity.PurchasedMovie;
import br.com.ractecnologia.exception.BusinessException;
import br.com.ractecnologia.repository.CustomerRepository;
import br.com.ractecnologia.repository.MovieRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private PurchasedMovieService purchasedMovieService;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<CustomerDTO> findAll() {
		List<Customer> list = customerRepository.findAll();
		return list.stream().map(obj -> modelMapper.map(obj, CustomerDTO.class)).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO findById(Long id) {
		Customer customer = customerRepository.findOne(id);
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}

	@Override
	public void delete(Long id) {
		customerRepository.delete(id);
	}

	@Override
	public void create(CustomerCreateDTO customerDTO) {
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		List<Customer> listEmail = customerRepository.findByEmail(customer.getEmail());
		if (!listEmail.isEmpty()) {
			throw new BusinessException("Email já cadastrado");
		}
		customer.setMoneySpent(BigDecimal.ZERO);
		customer.setStatus(CustomerStatus.REGULAR);
		customer.setStatusExpirationDate(null);
		customer.setId(null);
		customerRepository.save(customer);
	}

	@Override
	public void update(CustomerCreateDTO customerDTO) {
		
		Customer customer = customerRepository.findOne(customerDTO.getId());
		customer.setEmail(customerDTO.getEmail());
		customer.setName(customerDTO.getName());
		customerRepository.save(customer);
	}

	@Override
	public void promoteCustomer(Long id) {
		Customer customer = customerRepository.findOne(id);

		if (customer == null) {
			// 400 BadRequest
			throw new BusinessException("Cliente inexistente");
		}

		if (customer.getStatus() == CustomerStatus.ADVANCED && (Objects.isNull(customer.getStatusExpirationDate())
				|| customer.getStatusExpirationDate().before(new Date()))) {
			throw new BusinessException("O clinte já tem o status advanced");
		}

		boolean success = promoteCustomer(customer);

		if (!success) {
			// 400 BadRequest
			throw new BusinessException("Cliente não pode ser promovido");
		}

		customerRepository.save(customer);

	}

	private boolean promoteCustomer(Customer customer) {

		// at least 2 active movies during the last 30 days
		// if (customer.getPurchasedMovies()
		// .Count(x -> x.ExpirationDate == null || x.getExpirationDate() >=
		// DateTime.UtcNow.AddDays(-30)) < 2)
		// return false;

		Calendar calendar30Dias = Calendar.getInstance();
		calendar30Dias.add(Calendar.DAY_OF_MONTH, -30);
		Date dataMenos30Dias = calendar30Dias.getTime();
		int count = 0;
		for (PurchasedMovie purchasedMovie : customer.getPurchasedMovies()) {

			if (Objects.isNull(purchasedMovie.getExpirationDate())
					|| purchasedMovie.getExpirationDate().after(dataMenos30Dias)) {
				count++;
			}
		}
		if (count < 2) {
			return false;
		}

		// at least 100 dollars spent during the last year
		// if (customer.getPurchasedMovies().Where(
		// x -> x.PurchaseDate > DateTime.UtcNow.AddYears(-1)).Sum(x -> x.Price) < 100)
		// return false;

		Calendar calendarMenos1Dia = Calendar.getInstance();
		calendarMenos1Dia.add(Calendar.DAY_OF_MONTH, -1);
		Date dataMenos1Dias = calendarMenos1Dia.getTime();
		BigDecimal price = BigDecimal.ZERO;
		for (PurchasedMovie purchasedMovie : customer.getPurchasedMovies()) {
			if (purchasedMovie.getPurchaseDate().after(dataMenos1Dias)) {
				price = price.add(purchasedMovie.getPrice());
			}
		}

		if (price.intValue() < 10) {
			return false;

		}

		Calendar calendarAdvanced = Calendar.getInstance();
		calendarAdvanced.add(Calendar.YEAR, 1);
		customer.setStatus(CustomerStatus.ADVANCED);
		customer.setStatusExpirationDate(calendarAdvanced.getTime());

		return true;
	}

	@Override
	public void purchaseMovie(Long id, Long movieId) {
		Movie movie = movieRepository.findOne(movieId);
		Customer customer = customerRepository.findOne(id);

		// if ( movie == null ) 400 BadRequest

		// if ( customer == null ) 400 BadRequest

		// if (customer.PurchasedMovies.Any(x => x.MovieId == movie.Id &&
		// (x.ExpirationDate == null ||
		// x.ExpirationDate.Value >= DateTime.UtcNow)))

		purchasedMovieService.purchaseMovie(customer, movie);

	}

}

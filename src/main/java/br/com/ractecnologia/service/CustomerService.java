package br.com.ractecnologia.service;

import java.util.List;

import br.com.ractecnologia.dto.CustomerCreateDTO;
import br.com.ractecnologia.dto.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> findAll();

	CustomerDTO findById(Long id);

	void delete(Long id);

	void create(CustomerCreateDTO customerDTO);

	void update(CustomerCreateDTO customerDTO);

	void purchaseMovie(Long id, Long movieId);

	void promoteCustomer(Long id);


}

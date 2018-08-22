package br.com.ractecnologia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ractecnologia.entity.Customer;
import java.lang.String;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	 List<Customer> findByEmail(String email);

}

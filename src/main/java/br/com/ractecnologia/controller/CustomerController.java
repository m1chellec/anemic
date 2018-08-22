package br.com.ractecnologia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ractecnologia.dto.CustomerCreateDTO;
import br.com.ractecnologia.dto.CustomerDTO;
import br.com.ractecnologia.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	public CustomerController() {
		super();
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> findAll(){
		return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Long id){
		return new ResponseEntity<>(customerService.findById(id) ,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CustomerCreateDTO customerDTO){
		customerService.create(customerDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody CustomerCreateDTO customerDTO){
		customerService.update(customerDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		customerService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/movies")
	public ResponseEntity<Void> purchaseMovie(@PathVariable("id") Long id, @RequestBody  Long movieId){
		customerService.purchaseMovie(id,movieId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/promotion")
	public ResponseEntity<Void> promoteCustomer(@PathVariable("id") Long id){
		customerService.promoteCustomer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}

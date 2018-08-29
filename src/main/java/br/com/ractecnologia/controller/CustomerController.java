package br.com.ractecnologia.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ractecnologia.dto.CustomerCreateDTO;
import br.com.ractecnologia.dto.CustomerDTO;
import br.com.ractecnologia.dto.MovieIdDTO;
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
	@PreAuthorize("hasAuthority('ROLE_ADMIN')") //#oauth2.hasScope('toll_read') and 
	public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Long id){
		return new ResponseEntity<>(customerService.findById(id) ,HttpStatus.OK);
	}
	
	@GetMapping("/find")
	public ResponseEntity<CustomerDTO> findById2(HttpServletRequest req){
		String idStr = req.getParameter("id");
		
		CustomerDTO resp;
		HttpStatus status;
		if(Objects.isNull(idStr) || !StringUtils.isNumeric(idStr) ) {
			status = HttpStatus.BAD_REQUEST;
			resp = null;
		}else {
			
			Long id = Long.valueOf(idStr);
			resp = customerService.findById(id);
			status = HttpStatus.OK;
			
			
		}
		
		
		
		return new ResponseEntity<>(resp ,status);
		
		
	}
	
	
	
	@GetMapping(path= {"/producer"}, produces= { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE }   )
	public ResponseEntity<String> findById3(){ //@RequestHeader(value="Accept", defaultValue="text/plain") String accept
		ObjectMapper mapper = new ObjectMapper();
		CustomerDTO obj = customerService.findById(1L);
		String jsonInString = "";
		try {
			 jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<>(jsonInString, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{statusExpirationDate}/expiration")  //ddMMyyyy
	public ResponseEntity<List<CustomerDTO>> findByExpirateDate(
			@DateTimeFormat(pattern="ddMMyyyy") @PathVariable("statusExpirationDate") Date statusExpirationDate , 
			HttpServletRequest request,
			@RequestParam String sort){
		System.out.println(statusExpirationDate);
		String cnpj = request.getHeader("cnpj");
		if(Objects.isNull(cnpj) || !cnpj.equals("17")) {
			throw new RuntimeException("Necessita de mais informacoes.");
		}
		return new ResponseEntity<>(HttpStatus.OK);
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
	public ResponseEntity<Void> purchaseMovie(@PathVariable("id") Long id, @RequestBody  MovieIdDTO movieId){
		customerService.purchaseMovie(id,movieId.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/promotion")
	public ResponseEntity<Void> promoteCustomer(@PathVariable("id") Long id){
		customerService.promoteCustomer(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

}

package com.example.api.web.rest;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@ApiOperation(value = "Recupera todos os Customers")
	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Recupera o Customer do ID dado")
	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@ApiOperation(value = "Cria um Customer")
	@PostMapping
	public Customer create(@RequestBody(required = true) Customer customer) {
		Customer customerCreated = service.create(customer);
		return customerCreated;
	}

	@ApiOperation(value = "Atualiza um Customer")
	@PutMapping
	public Customer update(@RequestBody(required = true) Customer customer) {
		Customer customerUpdated = service.update(customer);
		return customerUpdated;
	}

	@ApiOperation(value = "Deleta um Customer dado o ID")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}

}

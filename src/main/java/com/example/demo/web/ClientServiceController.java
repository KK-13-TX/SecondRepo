package com.example.demo.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dao.ClientDao;
import com.example.demo.model.Client;

@RestController
public class ClientServiceController {

	@Autowired
	private ClientDao clientDao;

	@RequestMapping(value = "/Clients", method = RequestMethod.GET)
	public List<Client> getAllClients() {
		System.out.println("retreive all clients ...");
		return clientDao.findAll();

	}

	@GetMapping(value = "/Clients/{id}")
	public Client getClient(@PathVariable int id) {
		return clientDao.findById(id);
	}

	@GetMapping(value = "/Clients/Ages/{age}")
	public List<Client> getClientAgeGreaterThan(@PathVariable int age) {
		return clientDao.findByAgeGreaterThan(age);
	}

	@GetMapping(value = "/Clients/Names/{recherche}")
	public List<Client> getClientsByName(@PathVariable String recherche) {
		return clientDao.findByNameLike(recherche);

	}

	@PostMapping(value = "/Clients")
	public ResponseEntity<Void> addClient(@RequestBody Client client) {
		Client clientAdded = clientDao.save(client);
		if (clientAdded == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientAdded.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "/Clients")
	public void updateClient(@RequestBody Client client) {
		clientDao.save(client);
	}

	@DeleteMapping(value = "/Clients/{id}")
	public void deleteClient(@PathVariable int id) {
		clientDao.deleteById(id);
	}

}

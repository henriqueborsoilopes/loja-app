package com.curso.lojaapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.lojaapp.domain.Cliente;
import com.curso.lojaapp.repositories.ClienteRepository;
import com.curso.lojaapp.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository categoriaRespository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = categoriaRespository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( "Obejto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}

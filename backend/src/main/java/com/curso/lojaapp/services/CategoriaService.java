package com.curso.lojaapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.lojaapp.domain.Categoria;
import com.curso.lojaapp.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRespository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRespository.findById(id);
		return obj.orElse(null);
	}
}

package com.curso.lojaapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.lojaapp.domain.Cidade;
import com.curso.lojaapp.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findEstado(Integer estadoId){
		return cidadeRepository.findEstado(estadoId);
	}
}

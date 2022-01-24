package com.curso.lojaapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.lojaapp.domain.Pedido;
import com.curso.lojaapp.repositories.PedidoRepository;
import com.curso.lojaapp.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRespository;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRespository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( "Obejto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}

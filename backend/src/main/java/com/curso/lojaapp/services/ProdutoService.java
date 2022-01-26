package com.curso.lojaapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.lojaapp.domain.Categoria;
import com.curso.lojaapp.domain.Cliente;
import com.curso.lojaapp.domain.Pedido;
import com.curso.lojaapp.domain.Produto;
import com.curso.lojaapp.repositories.CategoriaRepository;
import com.curso.lojaapp.repositories.ProdutoRepository;
import com.curso.lojaapp.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( "Obejto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.findDistincByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
	public Pedido insert(Pedido obj) {
		return null;
	}
}

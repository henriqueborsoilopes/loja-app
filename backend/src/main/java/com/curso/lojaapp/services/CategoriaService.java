package com.curso.lojaapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.lojaapp.domain.Categoria;
import com.curso.lojaapp.dto.CategoriaDTO;
import com.curso.lojaapp.repositories.CategoriaRepository;
import com.curso.lojaapp.services.exception.DataIntegrityException;
import com.curso.lojaapp.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRespository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRespository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( "Obejto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaRespository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return categoriaRespository.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRespository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return categoriaRespository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRespository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
}

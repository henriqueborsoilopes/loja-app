package com.curso.lojaapp.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.lojaapp.domain.ItemPedido;
import com.curso.lojaapp.domain.PagamentoComBoleto;
import com.curso.lojaapp.domain.Pedido;
import com.curso.lojaapp.domain.enums.EstadoPagamento;
import com.curso.lojaapp.repositories.ItemPedidoRepository;
import com.curso.lojaapp.repositories.PagamentoRepository;
import com.curso.lojaapp.repositories.PedidoRepository;
import com.curso.lojaapp.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoRepository pedidoRespository;
	
	@Autowired
	private PagamentoRepository pagamentoRespository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRespository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( "Obejto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = pedidoRespository.save(obj);
		pagamentoRespository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}

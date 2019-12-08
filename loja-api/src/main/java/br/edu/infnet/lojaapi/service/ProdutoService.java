package br.edu.infnet.lojaapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lojaapi.model.Produto;
import br.edu.infnet.lojaapi.repository.ProdutoDao;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDao produtoDao;
	
	public ProdutoService() {
	}
	
	@Transactional
	public List<Produto> getAll() {
		
		return produtoDao.findAll();
	}
	
	@Transactional
	public Optional<Produto> findOne(Integer id) {
		
		return produtoDao.findById(id);
	}

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoDao.save(produto);
	}

	public Produto editar(Produto produto) throws ObjectNotFoundException  {
		
		Optional<Produto> prodSearch = this.findOne(produto.getId());
		
		if (prodSearch.isPresent()) {
			
			return produtoDao.save(produto);
		} else {
			
			return null;
		}
	}

	@Transactional
	public Boolean deletar(Integer id) throws ObjectNotFoundException {

		Optional<Produto> produto = this.findOne(id);
		
		if (produto.isPresent()) {
		
			produtoDao.deleteById(id); 
		
		} else {
			
			throw new ObjectNotFoundException(null);
		}

		return true;
	}
	
}

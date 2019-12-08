package br.edu.infnet.lojaapi.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.lojaapi.model.Cliente;
import br.edu.infnet.lojaapi.repository.ClienteDao;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService{

	@Autowired
	ClienteDao clienteDao;
	
	public ClienteService() {
	}
	
	@Transactional
	public List<Cliente> getAll(){
		
		return clienteDao.findAll();
	}
	
	@Transactional
	public Optional<Cliente> findOne(Integer id) { 
	
		return clienteDao.findById(id);
	}

	@Transactional
	public Boolean deletar(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> cliente = this.findOne(id);
		
		if (cliente.isPresent()) {
		
			clienteDao.deleteById(id); 
		
		} else {
			
			throw new ObjectNotFoundException(null);
		}

		return true;		
		
	}

	@Transactional
	public Cliente salvar(Cliente cliente) throws DataIntegrityViolationException {
		
		try {
			return clienteDao.save(cliente);
		
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityViolationException("duplicate entry");
		}

	}

	@Transactional
	public Cliente editar(Cliente cliente) throws ObjectNotFoundException {

		Optional<Cliente> cliSearch = this.findOne(cliente.getId());
		
		if (cliSearch.isPresent()) {
			
			return clienteDao.save(cliente);
		} else {
			
			throw new ObjectNotFoundException(null);
		}		
	}
	
	public ClienteDao getClienteDao() {
		return clienteDao;
	}

	public void setClienteDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

}

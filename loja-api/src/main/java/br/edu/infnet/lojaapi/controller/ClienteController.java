package br.edu.infnet.lojaapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lojaapi.model.Cliente;
import br.edu.infnet.lojaapi.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@ApiOperation(value = "Retorna uma lista de Clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista retornada com sucesso"),
			@ApiResponse(code = 204, message = "Não há conteúdo"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})		
	@GetMapping(value = "/clientes")
	public ResponseEntity<List<Cliente>> getAllClientes(){
		
		try {
		
			List<Cliente> clientes = clienteService.getAll();
			
			if (clientes.isEmpty()) {
			
				return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
			
		} catch (Exception e){
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "Retorna um Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente retornada com sucesso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})		
	@GetMapping(value = "/clientes/{id}")
	public ResponseEntity<Cliente> getOneCliente(@PathVariable("id") Integer id) {
		
		try {
			Optional<Cliente> cliente = clienteService.findOne(id);
			
			if(cliente.isPresent()) {
				return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@ApiOperation(value = "Cria um Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente criado com sucesso"),
			@ApiResponse(code = 422, message = "Cliente duplicado"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})		
	@PostMapping(value = "/clientes")
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){

		try {
			return new ResponseEntity<Cliente>(clienteService.salvar(cliente),HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "Remove um Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Cliente removido com sucesso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})		
	@DeleteMapping(value = "/clientes/{id}")
	public ResponseEntity<Object> deleteCliente(@PathVariable("id") Integer id){
		
		try {
			if(clienteService.deletar(id)) {
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
		
	}
	
	@ApiOperation(value = "Edita um Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente editado com sucesso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})		
	@PutMapping(value = "/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable("id") Integer id){
		
		cliente.setId(id);
		
		try {
			
			return new ResponseEntity<Cliente>(clienteService.editar(cliente), HttpStatus.CREATED);
			
		} catch (ObjectNotFoundException e) {
		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} catch (Exception e) {
			
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
}

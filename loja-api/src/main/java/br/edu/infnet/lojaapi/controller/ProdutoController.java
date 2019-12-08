package br.edu.infnet.lojaapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lojaapi.model.Produto;
import br.edu.infnet.lojaapi.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.tools.rmi.ObjectNotFoundException;


@RestController
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
		
	@ApiOperation(value = "Devolve uma lista de Produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista retornada com sucesso"),
			@ApiResponse(code = 204, message = "Nenhum produto a ser exibido."),
			@ApiResponse(code = 500, message = "Erro no servidor.")
	})	
	@GetMapping(value = "/produtos", produces = "application/json")
	public ResponseEntity<List<Produto>> listaTodos() {
		
		try {
			List<Produto> produtos = produtoService.getAll();
			
			if (produtos.isEmpty()) {
				
				return new ResponseEntity<List<Produto>>(produtos, HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
		} catch (Exception e){
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	@ApiOperation(value = "Retorna um Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Produto retornado com sucesso"),
			@ApiResponse(code = 404, message = "Erro na chamada da API"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})	
	@GetMapping(value = "/produtos/{id}", produces = "application/json")
	public ResponseEntity<Produto> getOne(@PathVariable("id") Integer id) {
		try {
			Optional<Produto> produto = produtoService.findOne(id); 
			
			if (produto.isPresent()) {
				return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@ApiOperation(value = "Cria um Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Produto criado com sucesso"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})	
	@PostMapping(value = "/produtos", 
			produces = {"application/json", "application/xml"}, 
			consumes = {"application/json", "application/xml"})
	public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
		try {
			return new ResponseEntity<Produto>(produtoService.salvar(produto),HttpStatus.CREATED);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@ApiOperation(value = "Edita um Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Produto editado com sucesso"),
			@ApiResponse(code = 404, message = "Erro na chamada da API"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})	
	@PutMapping(value = "/produtos/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Produto> edit(@RequestBody Produto produto, @PathVariable("id") Integer id) {
	
		try {
			
			return new ResponseEntity<Produto>(produtoService.editar(produto), HttpStatus.CREATED);
			
		} catch (ObjectNotFoundException e) {
		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		} catch (Exception e) {
			
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}		
	}


	@ApiOperation(value = "Remove um Produto")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Produto removido com sucesso"),
			@ApiResponse(code = 404, message = "Erro na chamada da API"),
			@ApiResponse(code = 500, message = "Erro no processamento da chamada")
	})		
	@DeleteMapping(value = "/produtos/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		
		try {
			if(produtoService.deletar(id)) {
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	
	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
}

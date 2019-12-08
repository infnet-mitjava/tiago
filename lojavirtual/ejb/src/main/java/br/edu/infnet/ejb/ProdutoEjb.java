package br.edu.infnet.ejb;

import java.net.ConnectException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.infnet.modelo.Produto;


//EJB que atua como um REST Client efetuando o cadastro e recuperando Produtos

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProdutoEjb {

	@PersistenceContext(unitName = "lojavirtual")
	private EntityManager em;	
	
	private static final String API_ADDRESS = "http://127.0.0.1:8085/loja-api";
	
	private Client client =  ClientBuilder.newClient();
	
	WebTarget webTarget = client.target(API_ADDRESS);	

	public List<Produto> getAll() throws ConnectException {

		try {
			return client
					.target(API_ADDRESS)
					.path("produtos")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Produto>>(){});

		} catch (ProcessingException e) {
			throw new ProcessingException(e);
		}
		
	}	
	
	public Produto saveProduto(Produto produto) throws ConnectException {

		try {
			Response response =  client
								.target(API_ADDRESS)
								.path("produtos")
								.request(MediaType.APPLICATION_JSON)
								.post(Entity.entity(produto, MediaType.APPLICATION_JSON));

			return response.readEntity(Produto.class);
		} catch (ProcessingException e) {
			throw new ProcessingException(e);
		}
	}

	public Produto getProdutoById(Integer id) {

		return em.find(Produto.class, id);
	}	
	
}

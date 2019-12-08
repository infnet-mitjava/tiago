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

import br.edu.infnet.modelo.Cliente;

// EJB que atua como um REST Client efetuando o cadastro e recuperando Clientes

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteEjb {
	
	@PersistenceContext(unitName = "lojavirtual")
	private EntityManager em;

	private static final String API_ADDRESS = "http://127.0.0.1:8085/loja-api";
	
	private Client client =  ClientBuilder.newClient();
	
	WebTarget webTarget = client.target(API_ADDRESS);
	
	public List<Cliente> getAll() throws ConnectException {

		try {
			return client
					.target(API_ADDRESS)
					.path("clientes")
					.request(MediaType.APPLICATION_JSON)
					.get(new GenericType<List<Cliente>>(){});

		} catch (ProcessingException e) {
			throw new ProcessingException(e);
		}
		
	}
	
	public Cliente saveCliente(Cliente cliente) throws ConnectException {

		try {
			Response response =  client
								.target(API_ADDRESS)
								.path("clientes")
								.request(MediaType.APPLICATION_JSON)
								.post(Entity.entity(cliente, MediaType.APPLICATION_JSON));

			if (Integer.valueOf(response.getStatus()) == 422) {
				
				throw new RuntimeException();
			}			
			
			return response.readEntity(Cliente.class);
			
		} catch (ProcessingException e) {
			throw new ProcessingException(e);
		}
	}
	
	public Cliente getClienteById(Integer id) {
		
		return em.find(Cliente.class, id);
	}
}

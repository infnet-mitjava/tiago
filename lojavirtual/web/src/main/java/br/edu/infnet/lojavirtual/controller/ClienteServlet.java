package br.edu.infnet.lojavirtual.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.infnet.ejb.ClienteEjb;
import br.edu.infnet.modelo.Cliente;

// Servlet responsável pelo cadastro e exibição de Clientes

@WebServlet(name = "cliente", urlPatterns = "/cliente")
public class ClienteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteEjb clienteEjb;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setAttribute("listaClientes", clienteEjb.getAll());
		} catch(Exception e) {
			req.setAttribute("error", "Erro ao recuperar dados!");
		}
		req.getRequestDispatcher("/views/cliente/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cliente cliente = new Cliente(req.getParameter("username"), req.getParameter("email"));
		
		if ("".equals(cliente.getEmail()) || "".equals(cliente.getUsername())) {
			req.setAttribute("error", "Favor preencher os campos corretamente!");
		} else {

			try {
				clienteEjb.saveCliente(cliente);	
				req.setAttribute("cliente", cliente);
			} catch (RuntimeException e) {
				
				req.setAttribute("error", "Usuário já cadastrado");
			} catch (Exception e) {
				req.setAttribute("error", "Erro ao inserir!");
			}		
		}
		
		this.doGet(req, resp);
	}

	public ClienteEjb getClienteEjb() {
		return clienteEjb;
	}

	public void setClienteEjb(ClienteEjb clienteEjb) {
		this.clienteEjb = clienteEjb;
	}
	
}

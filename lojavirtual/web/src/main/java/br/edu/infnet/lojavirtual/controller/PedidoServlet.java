package br.edu.infnet.lojavirtual.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.infnet.ejb.CarrinhoEjb;
import br.edu.infnet.modelo.Carrinho;

@WebServlet(name = "pedido", urlPatterns = "/pedido")
public class PedidoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarrinhoEjb carrinhoEjb;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Carrinho> carrinhos = carrinhoEjb.getAll();
		
		req.setAttribute("listaCarrinhos", carrinhos);
		
		req.getRequestDispatcher("/views/pedido/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}

}

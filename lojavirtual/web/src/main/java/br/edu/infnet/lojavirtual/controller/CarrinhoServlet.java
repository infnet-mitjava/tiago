package br.edu.infnet.lojavirtual.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.infnet.ejb.ClienteEjb;
import br.edu.infnet.ejb.ProdutoEjb;
import br.edu.infnet.lojavirtual.jms.CarrinhoJms;
import br.edu.infnet.modelo.Carrinho;
import br.edu.infnet.modelo.Cliente;
import br.edu.infnet.modelo.Produto;

//Servlet responsável pela criação do Carrinho e envio para o checkout

@WebServlet(name = "carrinho", urlPatterns = "/carrinho")
public class CarrinhoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoEjb produtoEjb;
	
	@Inject
	private ClienteEjb clienteEjb;
	
	@Inject
	private CarrinhoJms carrinhoJms;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			req.setAttribute("listaProdutos", produtoEjb.getAll());
			req.setAttribute("listaClientes", clienteEjb.getAll());
		
		} catch (Exception e) {
			
			req.setAttribute("error", "Erro ao recuperar dados!");
		}
		
		req.getRequestDispatcher("/views/carrinho/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");

		try {
			Cliente cliente = clienteEjb.getClienteById(Integer.valueOf(req.getParameter("cliente")));
			
			List<Produto> produtos = new ArrayList<Produto>();
			
			String[] checkedIds = req.getParameterValues("check");
			
			if(checkedIds != null) {
				
				for(String id: checkedIds) {
			
					for(Integer i=0; i<Integer.valueOf((String)req.getParameter("quantidade-" + id)); i++) {

						produtos.add(produtoEjb.getProdutoById(Integer.valueOf(id)));
					}
				}
			}
			
			Carrinho carrinho = new Carrinho(cliente, produtos, req.getParameter("formaPagamento"));
			
			carrinhoJms.processarPagamento(carrinho);

		} catch (Exception e) {
			req.setAttribute("error", "Erro ao gravar dados!");
		} 
		
		req.getRequestDispatcher("/pedido").forward(req, resp);
	}

	public ProdutoEjb getProdutoEjb() {
		return produtoEjb;
	}

	public void setProdutoEjb(ProdutoEjb produtoEjb) {
		this.produtoEjb = produtoEjb;
	}

	public ClienteEjb getClienteEjb() {
		return clienteEjb;
	}

	public void setClienteEjb(ClienteEjb clienteEjb) {
		this.clienteEjb = clienteEjb;
	}

	public CarrinhoJms getCarrinhoJms() {
		return carrinhoJms;
	}

	public void setCarrinhoJms(CarrinhoJms carrinhoJms) {
		this.carrinhoJms = carrinhoJms;
	}
	
}

package br.edu.infnet.lojavirtual.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.infnet.ejb.ProdutoEjb;
import br.edu.infnet.modelo.Produto;

//Servlet responsável pelo cadastro e exibição de Produtos

@WebServlet(name = "produto" , urlPatterns = "/produto")
public class ProdutoServlet extends HttpServlet  {

	private static final long serialVersionUID = 2L;

	@Inject
	private ProdutoEjb produtoEjb;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setAttribute("listaProdutos", produtoEjb.getAll());
		} catch(Exception e) {
			req.setAttribute("error", "Erro ao recuperar dados!");
		}
		req.getRequestDispatcher("/views/produto/list.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Produto produto = new Produto(
						req.getParameter("nome"), 
						req.getParameter("descricao"),
						Double.valueOf(req.getParameter("preco")));

		
			if ("".equals(produto.getNome()) || "".equals(produto.getDescricao()) || produto.getPreco().isNaN()) {
				req.setAttribute("error", "Favor preencher os campos corretamente!");	
			}
		
			produtoEjb.saveProduto(produto);	
			req.setAttribute("produto", produto);
		} catch (NumberFormatException e) {
			req.setAttribute("error", "Favor preencher os campos corretamente!");		
		} catch (Exception e) {
			req.setAttribute("error", "Erro ao inserir!");
		}

		this.doGet(req, resp);		
	}

	public ProdutoEjb getProdutoEjb() {
		return produtoEjb;
	}

	public void setProdutoEjb(ProdutoEjb produtoEjb) {
		this.produtoEjb = produtoEjb;
	}
	
}

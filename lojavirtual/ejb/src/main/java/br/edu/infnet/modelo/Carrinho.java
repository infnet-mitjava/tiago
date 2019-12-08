package br.edu.infnet.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrinho implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	
	private String formaPagamento;
	
	private Boolean pago;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "carrinho_produto", 
	joinColumns = {@JoinColumn(name = "carrinho_id")}, 
	inverseJoinColumns = {@JoinColumn(name = "produto_id")})
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public Carrinho() {
	}

	
	
	public Carrinho(Cliente cliente, List<Produto> produtos, String formaPagamento) {
		this.cliente = cliente;
		this.produtos = produtos;
		this.formaPagamento = formaPagamento;
	}
	
	
	public void addProduto(Produto produto) {
		this.produtos.add(produto);
		produto.getCarrinhos().add(this);
	}
	
	public void removeProduto(Produto produto) {
		this.produtos.remove(produto);
		produto.getCarrinhos().remove(this);
	}	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	
	@Override
	public String toString() {
		
		return new StringBuffer("id:")
				.append(this.id)
				.append(";cliente:")
				.append(this.cliente)
				.append(";formaPagamento:")
				.append(this.formaPagamento)
				.append(";pago:")
				.append(this.pago)
				.append(";produtos:")
				.append(this.produtos)
				.toString();
	}
	
}

package br.edu.infnet.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	private Double preco;
	
	@ManyToMany(mappedBy = "produtos", fetch=FetchType.EAGER)
	List<Carrinho> carrinhos = new ArrayList<Carrinho>();
	
	public Produto() {
	}

	public Produto(String nome, String descricao, Double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public void addCarrinho(Carrinho c) {
		this.carrinhos.add(c);
		c.getProdutos().add(this);
	}
	
	public void removeCarrinho(Carrinho c) {
		this.carrinhos.remove(c);
		c.getProdutos().remove(this);
	}
	
	@Override
	public String toString() {
		
		return new StringBuffer()
				.append("id:")
				.append(this.id)
				.append(";nome:")
				.append(this.nome)
				.append(";descricao:")
				.append(this.descricao)
				.append(";preco:")
				.append(this.preco)
				.toString();
	}

	public List<Carrinho> getCarrinhos() {
		return carrinhos;
	}

	public void setCarrinhos(List<Carrinho> carrinhos) {
		this.carrinhos = carrinhos;
	}

}

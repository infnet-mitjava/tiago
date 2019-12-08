package br.edu.infnet.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String email;

	@OneToOne(mappedBy = "cliente", optional = true)
	private Carrinho carrinho;
	
	public Cliente() {
	}
	
	public Cliente(String username, String email) {
		
		this.username = username;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
	@Override
	public String toString() {
		return new StringBuffer("id:")
				.append(this.id)
				.append(";username:")
				.append(this.username)
				.append(";email:")
				.append(this.email)
				.append(";carrinho:")
				.append(this.carrinho)
				.toString();
				
	}
	
}

package br.edu.infnet.lojaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.lojaapi.model.Produto;

public interface ProdutoDao extends JpaRepository<Produto, Integer>{

}

package br.edu.infnet.lojaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.lojaapi.model.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Integer>{

}

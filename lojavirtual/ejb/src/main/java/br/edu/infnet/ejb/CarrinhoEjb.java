package br.edu.infnet.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.infnet.modelo.Carrinho;

@Stateless
public class CarrinhoEjb {
	
	@PersistenceContext(unitName = "lojavirtual")
	private EntityManager em;

	
	public Carrinho salvaCarrinho(Carrinho carrinho) {
		
		try {
			em.clear();
			em.persist(carrinho);
			em.flush();
			return carrinho;
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}	

	public List<Carrinho> getAll() {
		
		return em.createQuery("SELECT c FROM Carrinho c").getResultList();
	}

	public void efetuaPagamento(Carrinho carrinho) {

		try {
			
			em.clear();
			em.merge(carrinho);
			em.flush();			
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	

}

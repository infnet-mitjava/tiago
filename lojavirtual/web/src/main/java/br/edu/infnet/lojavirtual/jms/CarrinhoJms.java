package br.edu.infnet.lojavirtual.jms;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import br.edu.infnet.ejb.CarrinhoEjb;
import br.edu.infnet.modelo.Carrinho;

public class CarrinhoJms {

	@Resource(lookup = "java:/myjms/mycon")
	ConnectionFactory connectionFactory;
	
	@Resource(lookup = "java:/myjms/myqueue")
	Destination destination;	
	
	@Inject
	CarrinhoEjb carrinhoEjb;
	
	public void sendMessage(Carrinho carrinho) {
		
		try {
						
			QueueConnection connection = (QueueConnection) connectionFactory.createConnection();
			
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer producer = session.createProducer(destination);
			
			ObjectMessage message = session.createObjectMessage(carrinho);		
			
			producer.send(message);
			
			producer.close();
			session.close();
			connection.close();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		
		}		
		
	}

	public void processarPagamento(Carrinho carrinho) {

		try {
			
			carrinhoEjb.salvaCarrinho(carrinho);
			this.sendMessage(carrinho);			
			System.out.println("Carrinho numero " + carrinho.getId() + " enviado para pagamento!");
			
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}
	
}

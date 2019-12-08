package br.edu.infnet.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.edu.infnet.ejb.CarrinhoEjb;
import br.edu.infnet.modelo.Carrinho;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "java:/myjms/myqueue"
				)
})
public class PaymentMdb implements MessageListener {

	@Inject
	CarrinhoEjb carrinhoEjb;
	
	@Override
	public void onMessage(Message message) {
		
		if(message instanceof ObjectMessage) {
			try {
				
				ObjectMessage msg = (ObjectMessage) message;
							
				Carrinho carrinho = (Carrinho)msg.getObject();
				
				carrinho.setPago(true);
								
				carrinhoEjb.efetuaPagamento(carrinho);
				
				System.out.println("Carrinho numero " + carrinho.getId() + " pago!");
				
			} catch (Exception e) {
				System.err.println("ops");
			}
		}
	}	
}

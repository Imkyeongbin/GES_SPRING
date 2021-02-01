package com.oracle.transaction.command;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.oracle.transaction.dao.TicketDao;
import com.oracle.transaction.dto.TicketDto;

public class TicketCommand implements ITicketCommand {
	
	private TicketDao             ticketDao;
	private TransactionTemplate   transactionTemplate2;
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	public void setTransactionTemplate2(TransactionTemplate transactionTemplate2) {
		this.transactionTemplate2 = transactionTemplate2;
	}
	public TicketDao getTicketDao() {
		System.out.println("TicketCommand getTicketDao start..");
		return ticketDao;
	}

	@Override
	public void execute(final TicketDto ticketDto) {
		// Service --> propagationBehavior 0
//		ticketDto.setAmount("1");
//		ticketDao.buyTicket(ticketDto);
//		
//		ticketDto.setAmount("7");
//		ticketDao.buyTicket(ticketDto);
		
		
		
		transactionTemplate2.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				ticketDto.setAmount("1");
				ticketDao.buyTicket(ticketDto);
				
				ticketDto.setAmount("7");
				ticketDao.buyTicket(ticketDto);
				
			}
		});
	}

}

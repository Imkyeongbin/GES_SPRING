package com.oracle.transaction.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

import com.oracle.transaction.dto.TicketDto;

public class TicketDao {
	JdbcTemplate       template;
	TransactionTemplate transactionTemplate1;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public void setTransactionTemplate1(TransactionTemplate transactionTemplate1) {
		this.transactionTemplate1 = transactionTemplate1;
	}
	
	public TicketDao() {
		System.out.println(template);
	}
	public void buyTicket(TicketDto ticket) {
		System.out.println("buyTicket()");
		System.out.println("ticket.getConsumerId() : " + ticket.getConsumerId());
		System.out.println("ticket.getAmount() : " + ticket.getAmount());
		                              
		
	}
	
}

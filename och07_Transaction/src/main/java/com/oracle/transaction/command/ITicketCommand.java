package com.oracle.transaction.command;

import com.oracle.transaction.dto.TicketDto;

public interface ITicketCommand {
	public void execute(TicketDto ticketDto);
}

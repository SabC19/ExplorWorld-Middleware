package com.sabic.explorworld.service.impl;

import com.sabic.explorworld.dao.TicketDAO;
import com.sabic.explorworld.model.TicketDTO;
import com.sabic.explorworld.service.TicketService;

public class TicketServiceImpl implements TicketService {

	private TicketDAO ticketDAO;

	public TicketServiceImpl() {
		this.ticketDAO = new TicketDAO();
	}

	@Override
	public TicketDTO findById(Long id) {
		return ticketDAO.findById(id);
	}

	@Override
	public TicketDTO create(TicketDTO ticket) {
		return ticketDAO.create(ticket);
	}

	@Override
	public void update(TicketDTO ticket) {
		if (ticket.getId() != null) {
			ticketDAO.update(ticket);
		}
	}

	@Override
	public void delete(Long id) {
		if (id != null) {
			ticketDAO.delete(id);
		}
	}
}

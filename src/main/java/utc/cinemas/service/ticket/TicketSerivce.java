package utc.cinemas.service.ticket;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.TicketDto;

import java.util.Map;

public interface TicketSerivce {
    Response getListOfTickets(Map<String, String> filters);
    Response getTicketById(Long id);
    Response getAll();
    Response create(TicketDto ticketDto);
    Response update(TicketDto ticketDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}

package utc.cinemas.service.ticket;

import utc.cinemas.model.dto.Response;
import utc.cinemas.model.entity.Seat;

import java.util.List;
import java.util.Map;

public interface TicketSerivce {
    Response getListOfTickets(Map<String, String> filters);
    void createTicketBatch(Long showtimeId, List<Seat> seats) throws Exception;
    void deleteTicketBatch(Long showtimeId) throws Exception;
}

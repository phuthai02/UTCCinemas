package utc.cinemas.service.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.TicketDto;
import utc.cinemas.repository.TicketRepository;

import java.util.Map;

@Service
@Slf4j
public class TicketSerivceImpl implements TicketSerivce {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Response getListOfTickets(Map<String, String> filters) {
        return null;
    }

    @Override
    public Response getTicketById(Long id) {
        return null;
    }

    @Override
    public Response getAll() {
        return null;
    }

    @Override
    public Response create(TicketDto ticketDto) {
        return null;
    }

    @Override
    public Response update(TicketDto ticketDto) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }

    @Override
    public Response toggleStatus(Long id) {
        return null;
    }
}

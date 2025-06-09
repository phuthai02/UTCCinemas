package utc.cinemas.service.ticket;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.TicketDto;
import utc.cinemas.repository.TicketRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TicketSerivceImpl implements TicketSerivce {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Response getListOfTickets(Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            Long cinemaId = JsonUtils.convert(filters.get("cinemaId"), Long.class);
            Long roomId = JsonUtils.convert(filters.get("roomId"), Long.class);
            Long movieId = JsonUtils.convert(filters.get("movieId"), Long.class);
            Integer status = JsonUtils.convert(filters.get("status"), Integer.class);
            Pageable pageable = Utils.getPageable(filters);

            Page<TicketDto> resultPage = ticketRepository.findAllWithDuplicateFlag(search, cinemaId, roomId, movieId, status, pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("data", resultPage.getContent());
            response.put("totalPages", resultPage.getTotalPages());

            return Utils.createResponse(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            log.error("Error fetching tickets: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response toggleStatus(Long id) {
        try {
            DatabaseUtils.toggleStatus(id, ticketRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Ticket not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin vé");
        } catch (Exception e) {
            log.error("Error toggling status ticket: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        try {
            DatabaseUtils.deleteEntity(id, ticketRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Ticket not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin vé");
        } catch (Exception e) {
            log.error("Error deleting ticket: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
}

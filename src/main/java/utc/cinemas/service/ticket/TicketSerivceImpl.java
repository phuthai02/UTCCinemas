package utc.cinemas.service.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.dto.TicketDto;
import utc.cinemas.model.entity.Seat;
import utc.cinemas.model.entity.Ticket;
import utc.cinemas.model.entity.User;
import utc.cinemas.repository.TicketRepository;
import utc.cinemas.repository.UserRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

            Pageable pageable = Utils.getPageable(filters);

            Page<TicketDto> resultPage = ticketRepository.findAll(search, cinemaId, roomId, movieId, pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("data", resultPage.getContent());
            response.put("totalPages", resultPage.getTotalPages());

            return Utils.createResponse(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            log.error("Error fetching tickets: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Autowired
    private UserRepository userRepository;
//
//    @Override
//    public void createTicketBatch(Long showtimeId, List<Seat> seats) throws Exception {
//        Random random = new Random();
//        List<User> users = userRepository.findAll();
//        for (Seat seat : seats) {
//            Ticket ticket = new Ticket();
//            ticket.setShowtimeId(showtimeId);
//            ticket.setSeatId(seat.getId());
//            int status = random.nextInt(2);
//            ticket.setStatus(status);
//            Timestamp createdDate = new Timestamp(System.currentTimeMillis());
//            ticket.setCreatedDate(createdDate);
//            if (status == 1) {
//                ticket.setModifiedDate(createdDate);
//            } else {
//                int randomMinutes = random.nextInt(60) + 1;
//                long createdTimeMillis = createdDate.getTime();
//                long modifiedTimeMillis = createdTimeMillis + (randomMinutes * 60 * 1000);
//                Timestamp modifiedDate = new Timestamp(modifiedTimeMillis);
//                ticket.setUserId(users.get(random.nextInt(users.size())).getId());
//                ticket.setModifiedDate(modifiedDate);
//            }
//            if (!users.isEmpty()) {
//                User randomUser = users.get(random.nextInt(users.size()));
//                ticket.setCreatedUser(randomUser.getId());
//                ticket.setModifiedUser(randomUser.getId());
//            }
//            ticketRepository.save(ticket);
//        }
//    }

    @Override
    public void createTicketBatch(Long showtimeId, List<Seat> seats) throws Exception {
        for (Seat seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setShowtimeId(showtimeId);
            ticket.setSeatId(seat.getId());
            DatabaseUtils.createEntity(ticket, ticketRepository);
        }
    }

    @Override
    public void deleteTicketBatch(Long showtimeId) throws Exception {
        ticketRepository.deleteAllByShowtimeId(showtimeId);
    }
}

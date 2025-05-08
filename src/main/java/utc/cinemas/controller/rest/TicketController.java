package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.ticket.TicketSerivce;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/ticket")
@Slf4j
public class TicketController {

    @Autowired
    private TicketSerivce ticketSerivce;

    @GetMapping("get-list")
    public ResponseEntity<Response> getTickets(@RequestParam Map<String, String> filters) {
        log.info("Get list tickets with params: filters={}", filters);
        Response response = ticketSerivce.getListOfTickets(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

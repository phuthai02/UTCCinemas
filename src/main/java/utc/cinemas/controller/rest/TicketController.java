package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.TicketDto;
import utc.cinemas.service.ticket.TicketSerivce;
import utc.cinemas.util.JsonUtils;

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

    @GetMapping("get-all")
    public ResponseEntity<Response> getAll() {
        log.info("Get all tickets");
        Response response = ticketSerivce.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getTicketById(@PathVariable Long id) {
        log.info("Get ticket by id: id={}", id);
        Response response = ticketSerivce.getTicketById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createTicket(@RequestBody TicketDto ticketDto) {
        log.info("Create ticket with params: dto={}", JsonUtils.toString(ticketDto));
        Response response = ticketSerivce.create(ticketDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateTicket(@RequestBody TicketDto ticketDto) {
        log.info("Update ticket with params: dto={}", JsonUtils.toString(ticketDto));
        Response response = ticketSerivce.update(ticketDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("toggle-status/{id}")
    public ResponseEntity<Response> toggleStatus(@PathVariable Long id) {
        log.info("Toggle status id={}", id);
        Response response = ticketSerivce.toggleStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        log.info("Delete id={}", id);
        Response response = ticketSerivce.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

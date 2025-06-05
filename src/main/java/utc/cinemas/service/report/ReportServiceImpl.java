package utc.cinemas.service.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.ReportRequest;
import utc.cinemas.model.dto.Response;
import utc.cinemas.repository.TicketRepository;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public Response viewReport(ReportRequest request) {
        return null;
    }

    @Override
    public Response exportReport(ReportRequest request) {
        return null;
    }
}

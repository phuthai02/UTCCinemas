package utc.cinemas.service.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.ReportRequest;
import utc.cinemas.model.dto.ReportResponse;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.repository.TicketRepository;
import utc.cinemas.util.Utils;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Response generateReport(ReportRequest request) {
        try {
            ReportResponse response = ticketRepository.getReportTotalByCinemaId(request.getEntityId());
            return Utils.createResponse(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, e.getMessage());
        }
    }
}

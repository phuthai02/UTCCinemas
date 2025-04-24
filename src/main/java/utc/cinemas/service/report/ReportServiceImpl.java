package utc.cinemas.service.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.*;
import utc.cinemas.repository.TicketRepository;
import utc.cinemas.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Response generateReport(ReportRequest request) {
        try {
            ReportResponse response = ticketRepository.getReportTotalByCinemaId(request.getEntityId());
            response.setChartData(getCinemasRevenueChart());
            return Utils.createResponse(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, e.getMessage());
        }
    }

    private ChartData getCinemasRevenueChart() {
        List<Object[]> results = ticketRepository.getAllCinemasWithRevenue();

        ChartData chartData = new ChartData();
        List<String> labels = new ArrayList<>();
        List<Long> values = new ArrayList<>();

        for (Object[] row : results) {
            labels.add((String) row[0]);
            values.add(((Number) row[1]).longValue());
        }

        chartData.setLabels(labels);
        chartData.setValues(values);

        return chartData;
    }
}

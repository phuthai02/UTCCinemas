package utc.cinemas.service.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.report.ChartData;
import utc.cinemas.model.dto.report.ReportRequest;
import utc.cinemas.model.dto.report.ReportResponse;
import utc.cinemas.model.dto.report.TableData;
import utc.cinemas.repository.TicketRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private TicketRepository ticketRepository;

    public ReportResponse generateReport(ReportRequest request) {
        Timestamp[] dateRange = calculateDateRange(request.getDateRange());
        Timestamp startDate = dateRange[0];
        Timestamp endDate = dateRange[1];

        ReportResponse response = new ReportResponse();

        response.setTotalTickets(getTotalTickets(request.getReportType(), request.getEntityId(), startDate, endDate));
        response.setTotalRevenue(getTotalRevenue(request.getReportType(), request.getEntityId(), startDate, endDate));
        response.setTotalShowtimes(getTotalShowtimes(request.getReportType(), request.getEntityId(), startDate, endDate));
        response.setTotalViews(getTotalViews(request.getReportType(), request.getEntityId(), startDate, endDate));

        response.setChartData(getChartData(request, startDate, endDate));
//        response.setTableData(getTableData(request, startDate, endDate));

        return response;
    }

    private Long getTotalViews(String reportType, Long entityId, Timestamp startDate, Timestamp endDate) {
        return switch (reportType) {
            case "cinema" -> ticketRepository.getTotalViewsByCinema(entityId, startDate, endDate);
            case "room" -> ticketRepository.getTotalViewsByRoom(entityId, startDate, endDate);
            case "movie" -> ticketRepository.getTotalViewsByMovie(entityId, startDate, endDate);
            default -> ticketRepository.getTotalViewsAll(startDate, endDate);
        };
    }

    private Long getTotalTickets(String reportType, Long entityId, Timestamp startDate, Timestamp endDate) {
        return switch (reportType) {
            case "cinema" -> ticketRepository.getTotalTicketsByCinema(entityId, startDate, endDate);
            case "room" -> ticketRepository.getTotalTicketsByRoom(entityId, startDate, endDate);
            case "movie" -> ticketRepository.getTotalTicketsByMovie(entityId, startDate, endDate);
            default -> ticketRepository.getTotalTicketsAll(startDate, endDate);
        };
    }

    private Long getTotalRevenue(String reportType, Long entityId, Timestamp startDate, Timestamp endDate) {
        return switch (reportType) {
            case "cinema" -> ticketRepository.getTotalRevenueByCinema(entityId, startDate, endDate);
            case "room" -> ticketRepository.getTotalRevenueByRoom(entityId, startDate, endDate);
            case "movie" -> ticketRepository.getTotalRevenueByMovie(entityId, startDate, endDate);
            default -> ticketRepository.getTotalRevenueAll(startDate, endDate);
        };
    }

    private Long getTotalShowtimes(String reportType, Long entityId, Timestamp startDate, Timestamp endDate) {
        return switch (reportType) {
            case "cinema" -> ticketRepository.getTotalShowtimesByCinema(entityId, startDate, endDate);
            case "room" -> ticketRepository.getTotalShowtimesByRoom(entityId, startDate, endDate);
            case "movie" -> ticketRepository.getTotalShowtimesByMovie(entityId, startDate, endDate);
            default -> ticketRepository.getTotalShowtimesAll(startDate, endDate);
        };
    }

    private ChartData getChartData(ReportRequest request, Timestamp startDate, Timestamp endDate) {
        List<Object[]> rawData;
        switch (request.getReportType()) {
            case "cinema":
                rawData = ticketRepository.getChartDataByCinema(request.getEntityId(), startDate, endDate);
                break;
            case "room":
                rawData = ticketRepository.getChartDataByRoom(request.getEntityId(), startDate, endDate);
                break;
            case "movie":
                rawData = ticketRepository.getChartDataByMovie(request.getEntityId(), startDate, endDate);
                break;
            default:
                rawData = ticketRepository.getChartDataAll(startDate, endDate);
        }

        ChartData chartData = new ChartData();
        List<String> labels = new ArrayList<>();
        List<Long> values = new ArrayList<>();

        for (Object[] row : rawData) {
            if (row[0] != null) {
                labels.add(row[0].toString());
                values.add(row[1] != null ? ((Number) row[1]).longValue() : 0L);
            }
        }

        chartData.setLabels(labels);
        chartData.setValues(values);
        return chartData;
    }

    private List<TableData> getTableData(ReportRequest request, Timestamp startDate, Timestamp endDate) {
        List<Object[]> rawData;

        switch (request.getReportType()) {
            case "cinema":
                rawData = ticketRepository.getCinemaReport(request.getEntityId(), startDate, endDate);
                break;
            case "room":
                rawData = ticketRepository.getRoomReport(request.getEntityId(), startDate, endDate);
                break;
            case "movie":
                rawData = ticketRepository.getMovieReport(request.getEntityId(), startDate, endDate);
                break;
            default:
                return new ArrayList<>();
        }

        List<TableData> tableData = new ArrayList<>();
        Long totalRevenue = rawData.stream()
                .mapToLong(row -> row[2] != null ? ((Number) row[2]).longValue() : 0L)
                .sum();

        for (Object[] row : rawData) {
            TableData data = new TableData();
            data.setName((String) row[0]);
            data.setTicketCount(row[1] != null ? ((Number) row[1]).longValue() : 0L);
            data.setRevenue(row[2] != null ? ((Number) row[2]).longValue() : 0L);

            if (totalRevenue > 0) {
                data.setPercentage((data.getRevenue().doubleValue() / totalRevenue) * 100);
            } else {
                data.setPercentage(0.0);
            }

            tableData.add(data);
        }

        return tableData;
    }

    private Timestamp[] calculateDateRange(String dateRange) {
        if ("alltime".equals(dateRange)) return new Timestamp[]{null, null};

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = switch (dateRange) {
            case "today" -> now.toLocalDate().atStartOfDay();
            case "last7days" -> now.minusDays(7);
            case "last15days" -> now.minusDays(15);
            case "last30days" -> now.minusDays(30);
            default -> now.toLocalDate().atStartOfDay();
        };

        return new Timestamp[]{Timestamp.valueOf(start), Timestamp.valueOf(now)};
    }
}
package utc.cinemas.service.cinema;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.CinemasDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Cinema;
import utc.cinemas.repository.CinemaRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.Utils;

import java.util.Map;

@Service
@Slf4j
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public Response getListOfCinemas(Map<String, String> filters) {
        try {
            String search = filters.getOrDefault("search", "").trim();
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> cinemaRepository.findAll("%" + search + "%", pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching cinemas: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response create(CinemasDto cinemasDto) {
        try {
            Cinema cinema = cinemasDto.getEntity();
            DatabaseUtils.createEntity(cinema, cinemaRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm rạp chiếu mới thành công");
        } catch (Exception e) {
            log.error("Error adding cinemas: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm rạp chiếu mới thất bại");
        }
    }
}

package utc.cinemas.service.cinema;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.CinemaDto;
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
    public Response create(CinemaDto cinemaDto) {
        try {
            Cinema cinema = cinemaDto.getEntity();
            DatabaseUtils.createEntity(cinema, cinemaRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm rạp chiếu mới thành công");
        } catch (Exception e) {
            log.error("Error adding cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm rạp chiếu mới thất bại");
        }
    }

    @Override
    public Response getCinemaById(Long id) {
        try {
            Cinema cinema = cinemaRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, cinema);
        } catch (Exception e) {
            log.error("Error getting cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Tìm kiếm rạp chiếu thất bại");
        }
    }

    @Override
    public Response update(CinemaDto cinemaDto) {
        try {
            Cinema cinema = cinemaDto.getEntity();
            DatabaseUtils.updateEntity(cinema, cinemaRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật rạp chiếu thành công");
        } catch (Exception e) {
            log.error("Error editing cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật rạp chiếu thất bại");
        }
    }
}

package utc.cinemas.service.cinema;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.CinemasDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Cinema;
import utc.cinemas.repository.CinemaRepository;
import utc.cinemas.util.AuthUtils;
import utc.cinemas.util.Constants;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.Utils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public Response getListOfCinemas(Map<String, String> filters) {
        try {
            Integer page = Integer.parseInt(filters.getOrDefault("page", "0"));
            Integer pageSize = Integer.parseInt(filters.getOrDefault("pageSize", "20"));
            String sortBy = filters.getOrDefault("sortBy", "id");
            String sortOrder = filters.getOrDefault("sortOrder", "desc");
            String search = filters.getOrDefault("search", "");

            Sort.Direction direction = Sort.Direction.fromString(sortOrder);
            Pageable pageable = PageRequest.of(page, pageSize, Sort.by(direction, sortBy));
            Page<Cinema> cinemas;

            cinemas = cinemaRepository.findAll("%" + search.trim() + "%", pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("data", cinemas.getContent());
            response.put("totalPages", cinemas.getTotalPages());

            return Utils.createResponse(ResponseCode.SUCCESS, response);
        } catch (Exception e) {
            log.error("Error fetching movies: {}", e.getMessage());
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
            log.error("Error adding movie: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm rạp chiếu mới thất bại");
        }
    }
}

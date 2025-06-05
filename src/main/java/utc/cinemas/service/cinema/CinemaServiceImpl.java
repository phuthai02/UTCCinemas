package utc.cinemas.service.cinema;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.CinemaDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Cinema;
import utc.cinemas.repository.CinemaRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public Response getListOfCinemas(Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            Long directorId = JsonUtils.convert(filters.get("directorId"), Long.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> cinemaRepository.findAll(search,  directorId, pageable));
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
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm chi nhánh mới thành công");
        } catch (Exception e) {
            log.error("Error adding cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm chi nhánh mới thất bại");
        }
    }

    @Override
    public Response getCinemaById(Long id) {
        try {
            Cinema cinema = cinemaRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, cinema);
        } catch (Exception e) {
            log.error("Error getting cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin chi nhánh");
        }
    }

    @Override
    public Response update(CinemaDto cinemaDto) {
        try {
            Cinema cinema = cinemaDto.getEntity();
            DatabaseUtils.updateEntity(cinema, cinemaRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật chi nhánh thành công");
        } catch (Exception e) {
            log.error("Error editing cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật chi nhánh thất bại");
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Cinema> cinemas = cinemaRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, cinemas);
        } catch (Exception e) {
            log.error("Error fetching cinemas all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response toggleStatus(Long id) {
        try {
            DatabaseUtils.toggleStatus(id, cinemaRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Cinema not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin chi nhánh");
        } catch (Exception e) {
            log.error("Error toggling status cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        try {
            DatabaseUtils.deleteEntity(id, cinemaRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Cinema not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin chi nhánh");
        } catch (Exception e) {
            log.error("Error deleting cinema: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
}

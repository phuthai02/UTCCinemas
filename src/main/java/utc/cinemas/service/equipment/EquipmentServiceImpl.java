package utc.cinemas.service.equipment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.EquipmentDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    @Override
    public Response getListOfEquipments(Map<String, String> filters) {
        return null;
    }

    @Override
    public Response create(EquipmentDto equipmentDto) {
        return null;
    }

    @Override
    public Response getEquipmentById(Long id) {
        return null;
    }

    @Override
    public Response update(EquipmentDto equipmentDto) {
        return null;
    }

    @Override
    public Response getAll() {
        return null;
    }
}

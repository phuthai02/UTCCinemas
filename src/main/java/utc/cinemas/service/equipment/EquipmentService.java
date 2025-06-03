package utc.cinemas.service.equipment;

import utc.cinemas.model.dto.EquipmentDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface EquipmentService {
    Response getListOfEquipments(Map<String, String> filters);
    Response create(EquipmentDto equipmentDto);
    Response getEquipmentById(Long id);
    Response update(EquipmentDto equipmentDto);
    Response getAll();
}

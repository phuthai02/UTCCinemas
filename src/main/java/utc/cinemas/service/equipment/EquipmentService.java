package utc.cinemas.service.equipment;

import utc.cinemas.model.dto.EquipmentDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface EquipmentService {
    Response getListOfEquipments(Map<String, String> filters);
    Response getEquipmentById(Long id);
    Response getAll();
    Response create(EquipmentDto equipmentDto);
    Response update(EquipmentDto equipmentDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}

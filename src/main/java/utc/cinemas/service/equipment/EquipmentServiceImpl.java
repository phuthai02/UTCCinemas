package utc.cinemas.service.equipment;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.EquipmentDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Equipment;
import utc.cinemas.repository.EquipmentRepository;
import utc.cinemas.util.DatabaseUtils;
import utc.cinemas.util.JsonUtils;
import utc.cinemas.util.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Response getListOfEquipments(Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            String equipmentType = JsonUtils.convert(filters.get("equipmentType"), String.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> equipmentRepository.findAll(search, equipmentType, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching equipments: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response create(EquipmentDto equipmentDto) {
        try {
            Equipment equipment = equipmentDto.getEntity();
            DatabaseUtils.createEntity(equipment, equipmentRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm thiết bị mới thành công");
        } catch (Exception e) {
            log.error("Error adding equipment: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm thiết bị mới thất bại");
        }
    }

    @Override
    public Response getEquipmentById(Long id) {
        try {
            Equipment equipment = equipmentRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, equipment);
        } catch (Exception e) {
            log.error("Error getting equipment: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin thiết bị");
        }
    }

    @Override
    public Response update(EquipmentDto equipmentDto) {
        try {
            Equipment equipment = equipmentDto.getEntity();
            DatabaseUtils.updateEntity(equipment, equipmentRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật thiết bị thành công");
        } catch (Exception e) {
            log.error("Error editing equipment: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật thiết bị thất bại");
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Equipment> equipments = equipmentRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, equipments);
        } catch (Exception e) {
            log.error("Error fetching equipments all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getAllTypes() {
        try {
            List<Equipment> equipments = equipmentRepository.findAll();
            Set<String> modules = equipments.stream().map(Equipment::getEquipmentType).filter(Objects::nonNull).collect(Collectors.toSet());
            return Utils.createResponse(ResponseCode.SUCCESS, modules);
        } catch (Exception e) {
            log.error("Error fetching types all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response toggleStatus(Long id) {
        try {
            DatabaseUtils.toggleStatus(id, equipmentRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Equipment not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin thiết bị");
        } catch (Exception e) {
            log.error("Error toggling status equipment: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        try {
            DatabaseUtils.deleteEntity(id, equipmentRepository);
            return Utils.createResponse(ResponseCode.SUCCESS);
        } catch (EntityNotFoundException e) {
            log.error("Equipment not found for ID {}: {}", id, e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin thiết bị");
        } catch (Exception e) {
            log.error("Error deleting equipment: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }
}

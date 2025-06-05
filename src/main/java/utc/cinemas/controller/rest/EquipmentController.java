package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.EquipmentDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.equipment.EquipmentService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/equipments")
@Slf4j
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getEquipments(@RequestParam Map<String, String> filters) {
        log.info("Get list equipments with params: filters={}", filters);
        Response response = equipmentService.getListOfEquipments(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getEquipmentById(@PathVariable Long id) {
        log.info("Get equipment by id: id={}", id);
        Response response = equipmentService.getEquipmentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createEquipment(@RequestBody EquipmentDto equipmentDto) {
        log.info("Create equipment with params: dto={}", JsonUtils.toString(equipmentDto));
        Response response = equipmentService.create(equipmentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateEquipment(@RequestBody EquipmentDto equipmentDto) {
        log.info("Update equipment with params: dto={}", JsonUtils.toString(equipmentDto));
        Response response = equipmentService.update(equipmentDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getEquipments() {
        log.info("Get all equipments");
        Response response = equipmentService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-equipment-types")
    public ResponseEntity<Response> getEquipmentTypes() {
        log.info("Get all equipment types");
        Response response = equipmentService.getAllTypes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("toggle-status/{id}")
    public ResponseEntity<Response> toggleStatus(@PathVariable Long id) {
        log.info("Toggle status id={}", id);
        Response response = equipmentService.toggleStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        log.info("Delete id={}", id);
        Response response = equipmentService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

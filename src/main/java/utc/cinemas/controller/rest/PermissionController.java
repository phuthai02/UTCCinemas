package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.PermissionDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.permisstion.PermissionService;
import utc.cinemas.util.JsonUtils;


import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getPermissions(@RequestParam Map<String, String> filters) {
        log.info("Get list permissions with params: filters={}", filters);
        Response response = permissionService.getListOfPermissions(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getPermissionById(@PathVariable Long id) {
        log.info("Get permission by id: id={}", id);
        Response response = permissionService.getPermissionById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createPermission(@RequestBody PermissionDto permissionDto) {
        log.info("Create permission with params: dto={}", JsonUtils.toString(permissionDto));
        Response response = permissionService.create(permissionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updatePermission(@RequestBody PermissionDto permissionDto) {
        log.info("Update permission with params: dto={}", JsonUtils.toString(permissionDto));
        Response response = permissionService.update(permissionDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getPermissions() {
        log.info("Get all permissions");
        Response response = permissionService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-modules")
    public ResponseEntity<Response> getModules() {
        log.info("Get all modules");
        Response response = permissionService.getAllModules();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
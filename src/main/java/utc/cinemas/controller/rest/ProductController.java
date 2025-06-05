package utc.cinemas.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utc.cinemas.model.dto.ProductDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.service.product.ProductService;
import utc.cinemas.util.JsonUtils;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("get-list")
    public ResponseEntity<Response> getProducts(@RequestParam Map<String, String> filters) {
        log.info("Get list products with params: filters={}", filters);
        Response response = productService.getListOfProducts(filters);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        log.info("Get product by id: id={}", id);
        Response response = productService.getProductById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Response> createProduct(@RequestBody ProductDto productDto) {
        log.info("Create product with params: dto={}", JsonUtils.toString(productDto));
        Response response = productService.create(productDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Response> updateProduct(@RequestBody ProductDto productDto) {
        log.info("Update product with params: dto={}", JsonUtils.toString(productDto));
        Response response = productService.update(productDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Response> getProducts() {
        log.info("Get all products");
        Response response = productService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-product-types")
    public ResponseEntity<Response> getProductTypes() {
        log.info("Get all product types");
        Response response = productService.getAllProductTypes();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        log.info("Delete product with id: id={}", id);
        Response response = productService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("toggle-status/{id}")
    public ResponseEntity<Response> toggleStatus(@PathVariable Long id) {
        log.info("Toggle status id={}", id);
        Response response = productService.toggleStatus(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        log.info("Delete id={}", id);
        Response response = productService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
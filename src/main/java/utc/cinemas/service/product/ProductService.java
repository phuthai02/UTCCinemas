package utc.cinemas.service.product;

import utc.cinemas.model.dto.ProductDto;
import utc.cinemas.model.dto.Response;

import java.util.Map;

public interface ProductService {
    Response getListOfProducts(Map<String, String> filters);
    Response getProductById(Long id);
    Response getAll();
    Response create(ProductDto productDto);
    Response update(ProductDto productDto);
    Response delete(Long id);
    Response toggleStatus(Long id);
}

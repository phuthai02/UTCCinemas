package utc.cinemas.service.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utc.cinemas.model.dto.ProductDto;
import utc.cinemas.model.dto.Response;
import utc.cinemas.model.dto.ResponseCode;
import utc.cinemas.model.entity.Product;
import utc.cinemas.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Response getListOfProducts(Map<String, String> filters) {
        try {
            String search = Utils.getSearch(filters);
            String productType = JsonUtils.convert(filters.get("productType"), String.class);
            Map<String, Object> result = DatabaseUtils.getList(filters, pageable -> productRepository.findAll(search, productType, pageable));
            return Utils.createResponse(ResponseCode.SUCCESS, result);
        } catch (Exception e) {
            log.error("Error fetching products: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getProductById(Long id) {
        try {
            Product product = productRepository.findById(id).orElse(null);
            return Utils.createResponse(ResponseCode.SUCCESS, product);
        } catch (Exception e) {
            log.error("Error getting product: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Không thể tải thông tin sản phaamrf");
        }
    }

    @Override
    public Response create(ProductDto productDto) {
        try {
            Product product = productDto.getEntity();
            DatabaseUtils.createEntity(product, productRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Thêm sản phẩm mới thành công");
        } catch (Exception e) {
            log.error("Error adding product: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Thêm sản phẩm mới thất bại");
        }
    }

    @Override
    public Response update(ProductDto productDto) {
        try {
            Product product = productDto.getEntity();
            DatabaseUtils.updateEntity(product, productRepository);
            return Utils.createResponse(ResponseCode.SUCCESS, "Cập nhật sản phẩm thành công");
        } catch (Exception e) {
            log.error("Error editing product: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR, "Cập nhật sản phẩm thất bại");
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Product> products = productRepository.findAll();
            return Utils.createResponse(ResponseCode.SUCCESS, products);
        } catch (Exception e) {
            log.error("Error fetching products all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response getAllProductTypes() {
        try {
            List<Product> products = productRepository.findAll();
            Set<String> types = products.stream().map(Product::getProductType).filter(Objects::nonNull).collect(Collectors.toSet());
            return Utils.createResponse(ResponseCode.SUCCESS, types);
        } catch (Exception e) {
            log.error("Error fetching types all: {}", e.getMessage());
            return Utils.createResponse(ResponseCode.ERROR);
        }
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}

package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Product;

import java.sql.Timestamp;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private String productType;
    private Long price;
    private Integer quantity;
    private String description;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;
    private Integer display;
    private Long cinemaId;

    public Product getEntity() {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        return product;
    }
}
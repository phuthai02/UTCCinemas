package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Equipment;

import java.sql.Timestamp;

@Data
public class EquipmentDto {
    private Long id;
    private Long roomId;
    private String equipmentName;
    private String equipmentType;
    private String brand;
    private String model;
    private Integer quantity;
    private Integer conditionStatus;
    private Timestamp purchaseDate;
    private Timestamp warrantyExpiry;
    private String description;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;
    private Integer display;

    public Equipment getEntity() {
        Equipment entity = new Equipment();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}

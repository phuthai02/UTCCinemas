package utc.cinemas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "EQUIPMENTS")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
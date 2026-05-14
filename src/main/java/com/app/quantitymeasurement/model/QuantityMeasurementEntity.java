package com.app.quantitymeasurement.model;

import com.app.quantitymeasurement.unit.IMeasurable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurement_entity", indexes = {
	    @Index(name = "idx_operation", columnList = "operation"),
	    @Index(name = "idx_measurement_type", columnList = "this_measurement_type"),
	    @Index(name = "idx_created_at", columnList = "created_at")})
public class QuantityMeasurementEntity {
	
	public QuantityMeasurementEntity(Long id, double thisValue, String thisUnit, String thisMeasurementType,
			double thatValue, String thatUnit, String thatMeasurementType, String operation, double resultValue,
			String resultUnit, String resultMeasurementType, String resultString, boolean isError, String errorMessage,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.thisValue = thisValue;
		this.thisUnit = thisUnit;
		this.thisMeasurementType = thisMeasurementType;
		this.thatValue = thatValue;
		this.thatUnit = thatUnit;
		this.thatMeasurementType = thatMeasurementType;
		this.operation = operation;
		this.resultValue = resultValue;
		this.resultUnit = resultUnit;
		this.resultMeasurementType = resultMeasurementType;
		this.resultString = resultString;
		this.isError = isError;
		this.errorMessage = errorMessage;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "this_value", nullable = false)
    public double thisValue;
    @Column(name = "this_unit", nullable = false)
    public String thisUnit;
    @Column(name = "this_measurement_type", nullable = false)
    public String thisMeasurementType;

    @Column(name = "that_value", nullable = false)
    public double thatValue;
    @Column(name = "that_unit", nullable = false)
    public String thatUnit;
    @Column(name = "that_measurement_type", nullable = false)
    public String thatMeasurementType;
    
    @Column(name = "operation", nullable = false)
    public String operation;

    @Column(name = "result_value")
    public double resultValue;
    @Column(name = "result_unit")
    public String resultUnit;
    @Column(name = "result_measurement_type")
    public String resultMeasurementType;
    
    @Column(name = "result_string")
    public String resultString;
    
    @Column(name = "is_error")
    public boolean isError;
    
    @Column(name = "error_message")
    public String errorMessage;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    public QuantityMeasurementEntity(IMeasurable thisQuantity, IMeasurable thatQuantity, String operation, String result) {
        this.thisValue = thisQuantity.getValue();
        this.thisUnit = thisQuantity.getUnit().toString();
        this.thisMeasurementType = thisQuantity.getUnit().getClass().getSimpleName();
        
        this.thatValue = thatQuantity.getValue();
        this.thatUnit = thatQuantity.getUnit().toString();
        this.thatMeasurementType = thatQuantity.getUnit().getClass().getSimpleName();
        
        this.operation = operation;
        this.resultString = result;
        this.isError = false;
    }
    
    public QuantityMeasurementEntity(IMeasurable thisQuantity, IMeasurable thatQuantity, String operation, IMeasurable result) {
        this.thisValue = thisQuantity.getValue();
        this.thisUnit = thisQuantity.getUnit().toString();
        this.thisMeasurementType = thisQuantity.getUnit().getClass().getSimpleName();
        
        this.thatValue = thatQuantity.getValue();
        this.thatUnit = thatQuantity.getUnit().toString();
        this.thatMeasurementType = thatQuantity.getUnit().getClass().getSimpleName();
        
        this.operation = operation;
        this.resultValue = result.getValue();
        this.resultUnit = result.getUnit().toString();
        this.resultMeasurementType = result.getUnit().getClass().getSimpleName();
        this.isError = false;
    }
    
    public QuantityMeasurementEntity(IMeasurable thisQuantity, IMeasurable thatQuantity, String operation, String errorMessage, boolean isError) {
        this.thisValue = thisQuantity != null ? thisQuantity.getValue() : 0.0;
        this.thisUnit = thisQuantity != null ? thisQuantity.getUnit().toString() : "N/A";
        this.thisMeasurementType = thisQuantity != null ? thisQuantity.getUnit().getClass().getSimpleName() : "N/A";
        
        this.thatValue = thatQuantity != null ? thatQuantity.getValue() : 0.0;
        this.thatUnit = thatQuantity != null ? thatQuantity.getUnit().toString() : "N/A";
        this.thatMeasurementType = thatQuantity != null ? thatQuantity.getUnit().getClass().getSimpleName() : "N/A";
        
        this.operation = operation;
        this.errorMessage = errorMessage;
        this.isError = isError;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getThisValue() {
		return thisValue;
	}

	public void setThisValue(double thisValue) {
		this.thisValue = thisValue;
	}

	public String getThisUnit() {
		return thisUnit;
	}

	public void setThisUnit(String thisUnit) {
		this.thisUnit = thisUnit;
	}

	public String getThisMeasurementType() {
		return thisMeasurementType;
	}

	public void setThisMeasurementType(String thisMeasurementType) {
		this.thisMeasurementType = thisMeasurementType;
	}

	public double getThatValue() {
		return thatValue;
	}

	public void setThatValue(double thatValue) {
		this.thatValue = thatValue;
	}

	public String getThatUnit() {
		return thatUnit;
	}

	public void setThatUnit(String thatUnit) {
		this.thatUnit = thatUnit;
	}

	public String getThatMeasurementType() {
		return thatMeasurementType;
	}

	public void setThatMeasurementType(String thatMeasurementType) {
		this.thatMeasurementType = thatMeasurementType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public double getResultValue() {
		return resultValue;
	}

	public void setResultValue(double resultValue) {
		this.resultValue = resultValue;
	}

	public String getResultUnit() {
		return resultUnit;
	}

	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}

	public String getResultMeasurementType() {
		return resultMeasurementType;
	}

	public void setResultMeasurementType(String resultMeasurementType) {
		this.resultMeasurementType = resultMeasurementType;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public QuantityMeasurementEntity() {}
}

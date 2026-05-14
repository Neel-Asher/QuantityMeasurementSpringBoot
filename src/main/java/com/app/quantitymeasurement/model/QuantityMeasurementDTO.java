package com.app.quantitymeasurement.model;

import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuantityMeasurementDTO {
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

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}


	public double thisValue;
    public String thisUnit;
    private String thisMeasurementType;
    public double thatValue;
    public String thatUnit;
    private String thatMeasurementType;
    public String operation;
    public String resultString;
    public double resultValue;
    public String resultUnit;
    private String resultMeasurementType;
    private String errorMessage;
    
    @JsonProperty("error")
    public boolean error;

    public static QuantityMeasurementDTO from(QuantityMeasurementEntity entity) {
    	if (entity == null) {
			return null;
		} else {
			QuantityMeasurementDTO dto = new QuantityMeasurementDTO();
			dto.thisValue = entity.thisValue;
			dto.thisUnit = entity.thisUnit;
			dto.thisMeasurementType = entity.thisMeasurementType;
			dto.thatValue = entity.thatValue;
			dto.thatUnit = entity.thatUnit;
			dto.thatMeasurementType = entity.thatMeasurementType;
			dto.operation = entity.operation;
			dto.resultString = entity.resultString;
			dto.resultValue = entity.resultValue;
			dto.resultUnit = entity.resultUnit;
			dto.resultMeasurementType = entity.resultMeasurementType;
			dto.errorMessage = entity.errorMessage;
			dto.error = entity.isError;
			return dto;
		}
    }

    public QuantityMeasurementEntity toEntity() {
    	QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
		entity.thisValue = this.thisValue;
		entity.thisUnit = this.thisUnit;
		entity.thisMeasurementType = this.thisMeasurementType;
		entity.thatValue = this.thatValue;
		entity.thatUnit = this.thatUnit;
		entity.thatMeasurementType = this.thatMeasurementType;
		entity.operation = this.operation;
		entity.resultString = this.resultString;
		entity.resultValue = this.resultValue;
		entity.resultUnit = this.resultUnit;
		entity.resultMeasurementType = this.resultMeasurementType;
		entity.errorMessage = this.errorMessage;
		entity.isError = this.error;
		return entity;
    }

    public static List<QuantityMeasurementDTO> fromList(List<QuantityMeasurementEntity> entities) {
		return entities.stream()
			.map(QuantityMeasurementDTO::from)
			.collect(Collectors.toList());
	}

 
    public static List<QuantityMeasurementEntity> toEntityList(List<QuantityMeasurementDTO> dtos) {
		return dtos.stream()
			.map(QuantityMeasurementDTO::toEntity)
			.collect(Collectors.toList());
	}
    
}
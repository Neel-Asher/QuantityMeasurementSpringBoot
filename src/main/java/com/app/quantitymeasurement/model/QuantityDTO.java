package com.app.quantitymeasurement.model;

import java.util.logging.Logger;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.AssertTrue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A quantity with a value and a unit")
public class QuantityDTO {

    private static final Logger logger = Logger.getLogger(QuantityDTO.class.getName());

    @jakarta.validation.constraints.Positive(message = "Value must be greater than 0")
    public double value;

    public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public static Logger getLogger() {
		return logger;
	}

	@NotNull(message = "Unit cannot be null")
    public String unit;

    @NotNull(message = "Measurement type cannot be null")
    @Pattern(
        regexp = "LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit",
        message = "Measurement type must be one of: LengthUnit, VolumeUnit, WeightUnit, TemperatureUnit"
    )
    public String measurementType;

    public QuantityDTO() {}

    @AssertTrue(message = "Unit must match the measurement type")
    public boolean isValidUnit() {
        try {
            switch (measurementType) {
                case "LengthUnit":
                    com.app.quantitymeasurement.unit.LengthUnit.valueOf(unit);
                    break;
                case "VolumeUnit":
                    com.app.quantitymeasurement.unit.VolumeUnit.valueOf(unit);
                    break;
                case "WeightUnit":
                    com.app.quantitymeasurement.unit.WeightUnit.valueOf(unit);
                    break;
                case "TemperatureUnit":
                    com.app.quantitymeasurement.unit.TemperatureUnit.valueOf(unit);
                    break;
                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
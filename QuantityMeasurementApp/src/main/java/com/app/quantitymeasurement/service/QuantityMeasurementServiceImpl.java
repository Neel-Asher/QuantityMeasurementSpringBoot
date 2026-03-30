package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.OperationType;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.model.QuantityModel;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.LengthUnit;
import com.app.quantitymeasurement.unit.Quantity;
import com.app.quantitymeasurement.unit.WeightUnit;
import com.app.quantitymeasurement.unit.VolumeUnit;
import com.app.quantitymeasurement.unit.TemperatureUnit;
import com.app.quantitymeasurement.unit.Unit;
import com.app.quantitymeasurement.exception.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements QuantityMeasurementService {

    private static final Logger logger = Logger.getLogger(
        QuantityMeasurementServiceImpl.class.getName()
    );

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    public QuantityMeasurementDTO compare(QuantityDTO thisDTO, QuantityDTO thatDTO) {

        Quantity q1 = toQuantity(thisDTO);
        Quantity q2 = toQuantity(thatDTO);

        boolean result = q1.compare(q2);

        QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

        dto.setThisValue(thisDTO.value);
        dto.setThisUnit(thisDTO.unit);
        dto.setThisMeasurementType(thisDTO.measurementType);

        dto.setThatValue(thatDTO.value);
        dto.setThatUnit(thatDTO.unit);
        dto.setThatMeasurementType(thatDTO.measurementType);

        dto.setOperation("COMPARE");
        dto.setResultString(result ? "Equal" : "Not Equal");
        dto.setError(false);

        QuantityMeasurementEntity entity =
            new QuantityMeasurementEntity(q1, q2, "COMPARE", dto.getResultString());

        repository.save(entity);

        return dto;
    }

    @Override
    public QuantityMeasurementDTO convert(QuantityDTO thisDTO, QuantityDTO targetDTO) {

        Quantity q = toQuantity(thisDTO);

        Unit targetUnit;

        switch (targetDTO.measurementType) {
            case "LengthUnit":
                targetUnit = LengthUnit.valueOf(targetDTO.unit);
                break;
            case "VolumeUnit":
                targetUnit = VolumeUnit.valueOf(targetDTO.unit);
                break;
            case "WeightUnit":
                targetUnit = WeightUnit.valueOf(targetDTO.unit);
                break;
            case "TemperatureUnit":
                targetUnit = TemperatureUnit.valueOf(targetDTO.unit);
                break;
            default:
                throw new RuntimeException("Invalid target unit");
        }

        double base = q.toBase();
        double converted = targetUnit.fromBase(base);

        Quantity result = new Quantity(converted, targetUnit);

        QuantityMeasurementDTO dto =
            toResultDTO(thisDTO, thisDTO, result, "CONVERT");

        QuantityMeasurementEntity entity =
            new QuantityMeasurementEntity(q, q, "CONVERT", result);

        repository.save(entity);

        return dto;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityDTO thisDTO, QuantityDTO thatDTO) {
    	
    	try {
	        Quantity q1 = toQuantity(thisDTO);
	        Quantity q2 = toQuantity(thatDTO);
	
	        Quantity result = q1.add(q2);
	
	        QuantityMeasurementDTO dto = toResultDTO(thisDTO, thatDTO, result, "ADD");
	
	        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "ADD", result);
	
	        repository.save(entity);
	
	        return dto;
	        
    	} catch (Exception e) {
    		
    		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(null, null, "ADD", e.getMessage(), true);

    		repository.save(entity);

    		throw new RuntimeException(e.getMessage());
    	}
    }
    
    @Override
    public QuantityMeasurementDTO add(
        QuantityDTO thisDTO,
        QuantityDTO thatDTO,
        QuantityDTO targetDTO
    ) {
        Quantity q1 = toQuantity(thisDTO);
        Quantity q2 = toQuantity(thatDTO);
        Unit targetUnit = toQuantity(targetDTO).getUnit();

        double resultBase = q1.toBase() + q2.toBase();
        double converted = targetUnit.fromBase(resultBase);

        Quantity result = new Quantity(converted, targetUnit);

        QuantityMeasurementDTO dto =
            toResultDTO(thisDTO, thatDTO, result, "ADD");

        repository.save(new QuantityMeasurementEntity(q1, q2, "ADD", result));

        return dto;
    }

    @Override
    public QuantityMeasurementDTO subtract(QuantityDTO thisDTO, QuantityDTO thatDTO) {
    	try {
    		Quantity q1 = toQuantity(thisDTO);
	        Quantity q2 = toQuantity(thatDTO);
	
	        double resultBase = q1.toBase() - q2.toBase();
	        Quantity result = Quantity.fromBase(resultBase, q1.getUnit());
	        
	        QuantityMeasurementDTO dto = toResultDTO(thisDTO, thatDTO, result, "SUBTRACT");
	        
	        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(q1, q2, "SUBTRACT", result);
	
	        repository.save(entity);
	        
	        return dto;
    	} catch (Exception e) {
    		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(null, null, "SUBTRACT", e.getMessage(), true);

			repository.save(entity);

			throw new RuntimeException(e.getMessage());
    	}
    }

    @Override
    public QuantityMeasurementDTO subtract(
        QuantityDTO thisDTO,
        QuantityDTO thatDTO,
        QuantityDTO targetDTO
    ) {
        try {
            Quantity q1 = toQuantity(thisDTO);
            Quantity q2 = toQuantity(thatDTO);

            Unit targetUnit = toQuantity(targetDTO).getUnit();

            double resultBase = q1.toBase() - q2.toBase();
            double converted = targetUnit.fromBase(resultBase);

            Quantity result = new Quantity(converted, targetUnit);

            QuantityMeasurementDTO dto =
                toResultDTO(thisDTO, thatDTO, result, "SUBTRACT");

            QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q1, q2, "SUBTRACT", result);

            repository.save(entity);

            return dto;

        } catch (Exception e) {

            QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(null, null, "SUBTRACT", e.getMessage(), true);

            repository.save(entity);

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementDTO divide(QuantityDTO thisDTO, QuantityDTO thatDTO) {

        try {
            Quantity q1 = toQuantity(thisDTO);
            Quantity q2 = toQuantity(thatDTO);

            double result = q1.toBase() / q2.toBase();

            QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

            dto.setThisValue(thisDTO.value);
            dto.setThisUnit(thisDTO.unit);
            dto.setThisMeasurementType(thisDTO.measurementType);

            dto.setThatValue(thatDTO.value);
            dto.setThatUnit(thatDTO.unit);
            dto.setThatMeasurementType(thatDTO.measurementType);

            dto.setOperation("DIVIDE");
            dto.setResultValue(result);
            dto.setError(false);

            QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(q1, q2, "DIVIDE", String.valueOf(result));

            repository.save(entity);

            return dto;

        } catch (Exception e) {

            QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(null, null, "DIVIDE", e.getMessage(), true);

            repository.save(entity);

            throw new RuntimeException(e.getMessage());
        }
    }
    
    private QuantityMeasurementDTO toResultDTO(
    	    QuantityDTO thisDTO,
    	    QuantityDTO thatDTO,
    	    Quantity result,
    	    String operation
    	) {
    	    QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

    	    dto.setThisValue(thisDTO.getValue());
    	    dto.setThisUnit(thisDTO.getUnit());
    	    dto.setThisMeasurementType(thisDTO.getMeasurementType());

    	    dto.setThatValue(thatDTO.getValue());
    	    dto.setThatUnit(thatDTO.getUnit());
    	    dto.setThatMeasurementType(thatDTO.getMeasurementType());

    	    dto.setOperation(operation);
    	    dto.setResultValue(result.getValue());
    	    dto.setResultUnit(result.getUnit().toString());
    	    dto.setResultMeasurementType(result.getUnit().getClass().getSimpleName());

    	    dto.setError(false);

    	    return dto;
    	}
    
    private Quantity toQuantity(QuantityDTO dto) {
        Unit unit;

        switch (dto.measurementType) {
            case "LengthUnit":
                unit = LengthUnit.valueOf(dto.unit);
                break;
            case "VolumeUnit":
                unit = VolumeUnit.valueOf(dto.unit);
                break;
            case "WeightUnit":
                unit = WeightUnit.valueOf(dto.unit);
                break;
            case "TemperatureUnit":
                unit = TemperatureUnit.valueOf(dto.unit);
                break;
            default:
                throw new RuntimeException("Invalid measurement type: " + dto.measurementType);
        }

        return new Quantity(dto.value, unit);
    }
    
    @Override
    public List<QuantityMeasurementDTO> getOperationHistory(String operation) {
        List<QuantityMeasurementEntity> entities =
            repository.findByOperation(operation.toUpperCase());

        return QuantityMeasurementDTO.fromList(entities);
    }
    
    @Override
    public List<QuantityMeasurementDTO> getMeasurementsByType(String type) {
        List<QuantityMeasurementEntity> entities =
            repository.findByThisMeasurementType(type);

        return QuantityMeasurementDTO.fromList(entities);
    }
    
    @Override
    public long getOperationCount(String operation) {
        return repository.countByOperationAndIsErrorFalse(operation.toUpperCase());
    }
    
    @Override
    public List<QuantityMeasurementDTO> getErrorHistory() {
        List<QuantityMeasurementEntity> entities =
            repository.findByIsError(true);

        return QuantityMeasurementDTO.fromList(entities);
    }
}
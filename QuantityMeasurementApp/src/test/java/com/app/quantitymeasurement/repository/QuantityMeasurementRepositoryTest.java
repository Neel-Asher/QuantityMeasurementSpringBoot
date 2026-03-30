package com.app.quantitymeasurement.repository;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class QuantityMeasurementRepositoryTest {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Test
    void testSaveAndFindByOperation() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.thisValue = 1;
        entity.thisUnit = "METER";
        entity.thisMeasurementType = "LengthUnit";

        entity.thatValue = 100;
        entity.thatUnit = "CENTIMETER";
        entity.thatMeasurementType = "LengthUnit";

        entity.operation = "ADD";
        entity.resultValue = 2.0;
        entity.resultUnit = "METER";
        entity.resultMeasurementType = "LengthUnit";
        entity.isError = false;

        repository.save(entity);

        List<QuantityMeasurementEntity> result =
            repository.findByOperation("ADD");

        assertFalse(result.isEmpty());
        assertEquals("ADD", result.get(0).operation);
    }
    
    @Test
    void testFindByMeasurementType() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.thisValue = 2;
        entity.thisUnit = "METER";
        entity.thisMeasurementType = "LengthUnit";

        entity.thatValue = 1;
        entity.thatUnit = "METER";
        entity.thatMeasurementType = "LengthUnit";

        entity.operation = "SUBTRACT";
        entity.resultValue = 1.0;
        entity.resultUnit = "METER";
        entity.resultMeasurementType = "LengthUnit";
        entity.isError = false;

        repository.save(entity);

        List<QuantityMeasurementEntity> result =
            repository.findByThisMeasurementType("LengthUnit");

        assertFalse(result.isEmpty());
    }
    
    @Test
    void testFindByIsError() {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.operation = "ADD";
        entity.errorMessage = "Invalid";
        entity.isError = true;

        repository.save(entity);

        List<QuantityMeasurementEntity> errors =
            repository.findByIsError(true);

        assertFalse(errors.isEmpty());
    }
    
    
}
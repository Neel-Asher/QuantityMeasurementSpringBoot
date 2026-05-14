package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuantityMeasurementServiceImplTest {

    @InjectMocks
    private QuantityMeasurementServiceImpl service;

    @Mock
    private QuantityMeasurementRepository repository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd_Length() {

        QuantityDTO q1 = new QuantityDTO();
        q1.setValue(1);
        q1.setUnit("METER");
        q1.setMeasurementType("LengthUnit");

        QuantityDTO q2 = new QuantityDTO();
        q2.setValue(100);
        q2.setUnit("CENTIMETER");
        q2.setMeasurementType("LengthUnit");

        QuantityMeasurementDTO result = service.add(q1, q2);

        assertEquals(2.0, result.getResultValue());
        assertEquals("METER", result.getResultUnit());
        assertEquals("ADD", result.getOperation());

        verify(repository, times(1)).save(any());
    }
    
    @Test
    void testConvert_Length() {

        QuantityDTO input = new QuantityDTO();
        input.setValue(1);
        input.setUnit("METER");
        input.setMeasurementType("LengthUnit");

        QuantityDTO target = new QuantityDTO();
        target.setUnit("CENTIMETER");
        target.setMeasurementType("LengthUnit");

        QuantityMeasurementDTO result = service.convert(input, target);

        assertEquals(100.0, result.getResultValue());
        assertEquals("CENTIMETER", result.getResultUnit());

        verify(repository, times(1)).save(any());
    }
    
    @Test
    void testCompare_Equal() {

        QuantityDTO q1 = new QuantityDTO();
        q1.setValue(1);
        q1.setUnit("METER");
        q1.setMeasurementType("LengthUnit");

        QuantityDTO q2 = new QuantityDTO();
        q2.setValue(100);
        q2.setUnit("CENTIMETER");
        q2.setMeasurementType("LengthUnit");

        QuantityMeasurementDTO result = service.compare(q1, q2);

        assertEquals("Equal", result.getResultString());
    }
}
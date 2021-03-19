package br.com.waes.json.diff.services;

import br.com.waes.json.diff.exceptions.BusinessException;
import br.com.waes.json.diff.model.DiffSide;
import br.com.waes.json.diff.model.JsonComparison;
import br.com.waes.json.diff.repositories.JsonComparisonRepository;
import br.com.waes.json.diff.utils.BaseTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonDiffServiceImplTest extends BaseTest {

    private JsonDiffServiceImpl service;

    @Mock
    private JsonComparisonRepository repository;
    @Mock
    private Base64Service base64Service;

    @BeforeEach
    public void setup() {
        this.service = new JsonDiffServiceImpl(repository,base64Service,new Gson());
    }

    @Test
    @DisplayName("should throw exception when base64 is empty")
    public void throwExceptionWhenBase64IsEmpty() {

        JsonComparison comparison = new JsonComparison(10, DiffSide.LEFT,"");
        BusinessException actual = assertThrows(BusinessException.class, () -> this.service.validateJsonComparison(comparison));

        assertEquals("content base64 encode could not be empty",actual.getMessage());
        verifyNoInteractions(base64Service);
    }

    @Test
    @DisplayName("should thrown exception when json already exists")
    public void throwExceptionWhenJsonAlreadyExists() {
        //setup mocks
        JsonComparison json = new JsonComparison(10,DiffSide.LEFT,"eyJuYW1lIjogImZlbGlwZSBtb250ZWlybyJ9");
        when(repository.findByIdAndSide(anyLong(), any(DiffSide.class))).thenReturn(Optional.of(json));

        BusinessException actual = assertThrows(BusinessException.class, () -> this.service.validateJsonComparison(json));

        assertEquals("Json Comparison with id 10 already exist!",actual.getMessage());
        verifyNoInteractions(base64Service);
    }

    @Test
    @DisplayName("should throw exception when theres only one side to compare")
    public void throwExceptionWhenTheresOnlyOneSideToCompare() {
        //setup mocks
        when(this.repository.findByIdOrderBySide(10)).thenReturn(Arrays.asList(mock(JsonComparison.class)));

        BusinessException actual = assertThrows(BusinessException.class, () -> this.service.getLeftAndRightSide(10));

        assertEquals("Both sides of json comparison with id 10 was not found",actual.getMessage());
    }

    @Test
    @DisplayName("should return pair for left and right side")
    public void shouldReturnPairForLeftAndRightSide() {
        JsonComparison left = new JsonComparison(10,DiffSide.LEFT,"base64Encode");
        JsonComparison right = new JsonComparison(10,DiffSide.RIGHT,"base64Encode");
        when(this.repository.findByIdOrderBySide(10)).thenReturn(Arrays.asList(left,right));

        Pair<JsonComparison, JsonComparison> actual = this.service.getLeftAndRightSide(10);

        assertEquals(Pair.of(left,right),actual);
    }
}
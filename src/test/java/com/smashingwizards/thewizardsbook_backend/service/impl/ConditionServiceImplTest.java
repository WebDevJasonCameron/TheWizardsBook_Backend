package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.ConditionMapper;
import com.smashingwizards.thewizardsbook_backend.model.Condition;
import com.smashingwizards.thewizardsbook_backend.repository.ConditionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConditionServiceImplTest {

    @Mock
    private ConditionRepository conditionRepositoryMock;
    @Mock
    private ConditionMapper conditionMapperMock;

    @InjectMocks
    private ConditionServiceImpl conditionService;

    @Test
    void getAllConditions_shouldReturnLstOfConditionDTOs() {
        Condition condition = new Condition();
        condition.setId(1L);
        condition.setName("Test Condition");
        condition.setDescription("Test Description");

        ConditionDTO dto = new ConditionDTO(1L, "Test Condition", "Test Description");

        when(conditionRepositoryMock.findAll()).thenReturn(List.of(condition));
        when(conditionMapperMock.conditionToConditionDTO(condition)).thenReturn(dto);

        List<ConditionDTO> result = conditionService.getAllConditions();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Condition", result.get(0).getName());
        assertEquals("Test Description", result.get(0).getDescription());

        Mockito.verify(conditionRepositoryMock).findAll();
        Mockito.verify(conditionMapperMock).conditionToConditionDTO(condition);
    }

    @Test
    void getConditionById_shouldReturnConditionDTO() {
        Condition condition = new Condition();
        condition.setId(1L);
        condition.setName("Test Condition");
        condition.setDescription("Test Description");

        ConditionDTO dto = new ConditionDTO(1L, "Test Condition", "Test Description");

        when(conditionRepositoryMock.findById(1L)).thenReturn(Optional.of(condition));
        when(conditionMapperMock.conditionToConditionDTO(condition)).thenReturn(dto);

        ConditionDTO result = conditionService.getConditionById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Condition", result.getName());
        assertEquals("Test Description", result.getDescription());

        Mockito.verify(conditionRepositoryMock).findById(1L);
        Mockito.verify(conditionMapperMock).conditionToConditionDTO(condition);
    }

    @Test
    void getConditionById_shouldThrowException_whenNotFound() {
        when(conditionRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                conditionService.getConditionById(1L)
        );

        assertEquals("Condition not found", exception.getMessage());
        Mockito.verify(conditionRepositoryMock).findById(1L);
        Mockito.verify(conditionMapperMock, never()).conditionToConditionDTO(any());
    }

    @Test
    void createCondition_shouldSaveAndReturnConditionDTO() {
        ConditionDTO inputDto = new ConditionDTO(null, "Test Condition", "Test Description");
        Condition condition = new Condition();
        condition.setName("Test Condition");
        condition.setDescription("Test Description");

        Condition savedCondition = new Condition();
        savedCondition.setId(5L);
        savedCondition.setName("Test Condition");
        savedCondition.setDescription("Test Description");

        ConditionDTO savedDto = new ConditionDTO(5L, "Test Condition", "Test Description");

        when(conditionMapperMock.conditionDTOToCondition(inputDto)).thenReturn(condition);
        when(conditionRepositoryMock.save(condition)).thenReturn(savedCondition);
        when(conditionMapperMock.conditionToConditionDTO(savedCondition)).thenReturn(savedDto);

        ConditionDTO result = conditionService.createCondition(inputDto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Test Condition", result.getName());
        assertEquals("Test Description", result.getDescription());

        Mockito.verify(conditionMapperMock).conditionDTOToCondition(inputDto);
        Mockito.verify(conditionRepositoryMock).save(condition);
        Mockito.verify(conditionMapperMock).conditionToConditionDTO(savedCondition);
    }

    @Test
    void updateCondition_shouldUpdateAndReturnConditionDTO_whenFound() {
        Condition existingCondition = new Condition();
        existingCondition.setId(1L);
        existingCondition.setName("Old Name");
        existingCondition.setDescription("Old Description");

        ConditionDTO updateDto = new ConditionDTO(null, "New Name", "New Description");

        Condition updatedCondition = new Condition();
        updatedCondition.setId(1L);
        updatedCondition.setName("New Name");
        updatedCondition.setDescription("New Description");

        ConditionDTO updatedDto = new ConditionDTO(1L, "New Name", "New Description");

        when(conditionRepositoryMock.findById(1L)).thenReturn(Optional.of(existingCondition));
        when(conditionRepositoryMock.save(existingCondition)).thenReturn(updatedCondition);
        when(conditionMapperMock.conditionToConditionDTO(updatedCondition)).thenReturn(updatedDto);

        ConditionDTO result = conditionService.updateCondition(1L, updateDto);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("New Description", result.getDescription());

        Mockito.verify(conditionRepositoryMock).findById(1L);
        Mockito.verify(conditionRepositoryMock).save(existingCondition);
        Mockito.verify(conditionMapperMock).conditionToConditionDTO(updatedCondition);
    }

    @Test
    void updateCondition_shouldThrowException_whenNotFound() {
        ConditionDTO updateDto = new ConditionDTO(null, "New Name", "New Description");

        when(conditionRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                conditionService.updateCondition(1L, updateDto)
        );

        assertEquals("Condition not found", exception.getMessage());

        Mockito.verify(conditionRepositoryMock).findById(1L);
        Mockito.verify(conditionRepositoryMock, never()).save(any());
    }

    @Test
    void deleteCondition_shouldCallRepositoryDeleteById() {
        conditionService.deleteCondition(1L);

        Mockito.verify(conditionRepositoryMock).deleteById(1L);
    }
}

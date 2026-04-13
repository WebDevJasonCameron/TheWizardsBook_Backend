package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.ConditionDTO;
import com.smashingwizards.thewizardsbook_backend.model.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConditionMapperTest {

    @Autowired
    private ConditionMapper conditionMapper;

    @Test
    void conditionToConditionDTO_shouldMapFieldCorrectly() {
        Condition condition = new Condition();
        condition.setId(1L);
        condition.setName("Test Condition");
        condition.setDescription("Test Description");

        ConditionDTO dto = conditionMapper.conditionToConditionDTO(condition);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Condition", dto.getName());
        assertEquals("Test Description", dto.getDescription());
    }

    @Test
    void conditionDTOToCondition_shouldMapFieldCorrectly() {
        ConditionDTO dto = new ConditionDTO();
        dto.setId(2L);
        dto.setName("Test Condition");
        dto.setDescription("Test Description");

        Condition condition = conditionMapper.conditionDTOToCondition(dto);

        assertNotNull(condition);
        assertEquals(2L, condition.getId());
        assertEquals("Test Condition", condition.getName());
        assertEquals("Test Description", condition.getDescription());
    }
}

package com.smashingwizards.thewizardsbook_backend.mapper;

import com.smashingwizards.thewizardsbook_backend.dto.TtrpgDTO;
import com.smashingwizards.thewizardsbook_backend.model.Ttrpg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TtrpgMapperTest {

    @Autowired
    private TtrpgMapper ttrpgMapper;

    @Test
    void ttrpgToTtrpgDTO_shouldMapFieldCorrectly() {
        Ttrpg ttrpg = new Ttrpg();
        ttrpg.setId(1L);
        ttrpg.setName("Test TTRPG");
        ttrpg.setVersion("Test Version");

        TtrpgDTO dto = ttrpgMapper.ttrpgToTtrpgDTO(ttrpg);

        assertNotNull(dto);
        assertEquals(ttrpg.getId(), dto.getId());
        assertEquals(ttrpg.getName(), dto.getName());
        assertEquals(ttrpg.getVersion(), dto.getVersion());
    }

    @Test
    void ttrpgDTOToTtrpg_shouldMapFieldCorrectly() {
        TtrpgDTO dto = new TtrpgDTO();
        dto.setId(2L);
        dto.setName("Test TTRPG");
        dto.setVersion("Test Version");

        Ttrpg ttrpg = ttrpgMapper.ttrpgDTOToTtrpg(dto);

        assertNotNull(ttrpg);
        assertEquals(2L, ttrpg.getId());
        assertEquals("Test TTRPG", ttrpg.getName());
        assertEquals("Test Version", ttrpg.getVersion());
    }
}

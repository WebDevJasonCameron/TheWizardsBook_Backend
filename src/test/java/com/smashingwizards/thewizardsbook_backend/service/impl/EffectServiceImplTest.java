package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.EffectDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.EffectMapper;
import com.smashingwizards.thewizardsbook_backend.model.Effect;
import com.smashingwizards.thewizardsbook_backend.repository.EffectRepository;
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
public class EffectServiceImplTest {

    @Mock
    private EffectRepository effectRepositoryMock;
    @Mock
    private EffectMapper effectMapperMock;

    @InjectMocks
    private EffectServiceImpl effectService;

    @Test
    void getAllEffects_shouldReturnLstOfEffectDTOs() {
        Effect effect = new Effect();
        effect.setId(1L);
        effect.setName("Test Effect");
        effect.setSubEffect("Test SubEffect");

        EffectDTO dto = new EffectDTO(1L, "Test Effect", "Test SubEffect");

        when(effectRepositoryMock.findAll()).thenReturn(List.of(effect));
        when(effectMapperMock.effectToEffectDTO(effect)).thenReturn(dto);

        List<EffectDTO> result = effectService.getAllEffects();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Effect", result.get(0).getName());
        assertEquals("Test SubEffect", result.get(0).getSubEffect());

        Mockito.verify(effectRepositoryMock).findAll();
        Mockito.verify(effectMapperMock).effectToEffectDTO(effect);
    }

    @Test
    void getEffectById_shouldReturnEffectDTO() {
        Effect effect = new Effect();
        effect.setId(1L);
        effect.setName("Test Effect");
        effect.setSubEffect("Test SubEffect");

        EffectDTO dto = new EffectDTO(1L, "Test Effect", "Test SubEffect");

        when(effectRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(effect));
        when(effectMapperMock.effectToEffectDTO(effect)).thenReturn(dto);

        EffectDTO result = effectService.getEffectById(1L);

        assertNotNull(result);
        assertEquals("Test Effect", result.getName());
        assertEquals("Test SubEffect", result.getSubEffect());

        Mockito.verify(effectRepositoryMock).findById(1L);
        Mockito.verify(effectMapperMock).effectToEffectDTO(effect);
    }

    @Test
    void getEffectById_shouldThrowException_whenNotFound() {
        when(effectRepositoryMock.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> effectService.getEffectById(1L));

        assertEquals("Effect not found", exception.getMessage());
        Mockito.verify(effectRepositoryMock).findById(1L);
        Mockito.verify(effectMapperMock, never()).effectToEffectDTO(any());
    }

    @Test
    void createEffect_shouldSaveAndReturnEffectDTO() {
        EffectDTO dto = new EffectDTO(null, "Test Effect", "Test SubEffect");
        Effect effect = new Effect();
        effect.setName("Test Effect");
        effect.setSubEffect("Test SubEffect");

        Effect savedEffect = new Effect();
        savedEffect.setId(5L);
        savedEffect.setName("Test Effect");
        savedEffect.setSubEffect("Test SubEffect");

        EffectDTO savedDto = new EffectDTO(5L, "Test Effect", "Test SubEffect");

        when(effectMapperMock.effectDTOToEffect(dto)).thenReturn(effect);
        when(effectRepositoryMock.save(effect)).thenReturn(savedEffect);
        when(effectMapperMock.effectToEffectDTO(savedEffect)).thenReturn(savedDto);

        EffectDTO result = effectService.createEffect(dto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Test Effect", result.getName());
        assertEquals("Test SubEffect", result.getSubEffect());

        Mockito.verify(effectMapperMock).effectDTOToEffect(dto);
        Mockito.verify(effectRepositoryMock).save(effect);
        Mockito.verify(effectMapperMock).effectToEffectDTO(savedEffect);
    }

    @Test
    void updateEffect_shouldUpdateAndReturnEffectDTO_whenFound() {
        Effect existingEffect = new Effect();
        existingEffect.setId(1L);
        existingEffect.setName("Old Name");
        existingEffect.setSubEffect("Old SubEffect");

        EffectDTO updateDto = new EffectDTO(null, "New Name", "New SubEffect");

        Effect updatedEffect = new Effect();
        updatedEffect.setId(1L);
        updatedEffect.setName("New Name");
        updatedEffect.setSubEffect("New SubEffect");

        EffectDTO updatedDto = new EffectDTO(1L, "New Name", "New SubEffect");

        when(effectRepositoryMock.findById(1L)).thenReturn(Optional.of(existingEffect));
        when(effectRepositoryMock.save(existingEffect)).thenReturn(updatedEffect);
        when(effectMapperMock.effectToEffectDTO(updatedEffect)).thenReturn(updatedDto);

        EffectDTO result = effectService.updateEffect(1L, updateDto);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("New SubEffect", result.getSubEffect());

        Mockito.verify(effectRepositoryMock).findById(1L);
        Mockito.verify(effectRepositoryMock).save(existingEffect);
        Mockito.verify(effectMapperMock).effectToEffectDTO(updatedEffect);
    }

    @Test
    void updateCondition_shouldThrowException_whenNotFound() {
        EffectDTO updateDto = new EffectDTO(null, "New Name", "New SubEffect");

        when(effectRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> effectService.updateEffect(1L, updateDto));

        assertEquals("Effect not found", exception.getMessage());
        Mockito.verify(effectRepositoryMock).findById(1L);
        Mockito.verify(effectRepositoryMock, never()).save(any());
    }

    @Test
    void deleteEffect_shouldCallRepositoryDeleteById() {
        effectService.deleteEffect(1L);

        Mockito.verify(effectRepositoryMock).deleteById(1L);
    }

}

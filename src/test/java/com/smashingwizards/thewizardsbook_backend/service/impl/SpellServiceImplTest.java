package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellMapper;
import com.smashingwizards.thewizardsbook_backend.model.Spell;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpellServiceImplTest {

    @Mock
    private SpellRepository spellRepositoryMock;
    @Mock
    private SpellMapper spellMapperMock;

    @InjectMocks
    private SpellServiceImpl spellService;

    @Test
    void getAllSpells_shouldReturnLstOfSpellDTOs() {
        Spell spell = getSpell();

        SpellDTO spellDto = new SpellDTO(1L, "Test Name", "Test Level", "Test CastingTime", "Test Range", false, false, false, "Test Materials", "Test Duration", false, false, "Test School", "Test Description");

        when(spellRepositoryMock.findAll()).thenReturn(List.of(spell));
        when(spellMapperMock.spellToSpellDTO(spell)).thenReturn(spellDto);

        List<SpellDTO> result = spellService.getAllSpells();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(spellDto, result.get(0));

        verify(spellRepositoryMock).findAll();
        verify(spellMapperMock).spellToSpellDTO(spell);
    }

    @Test
    void getSpellById_shouldReturnSpellDTO() {
        Spell spell = getSpell();

        SpellDTO spellDto = new SpellDTO(1L, "Test Name", "Test Level", "Test CastingTime", "Test Range", false, false, false, "Test Materials", "Test Duration", false, false, "Test School", "Test Description");

        when(spellRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(spell));
        when(spellMapperMock.spellToSpellDTO(spell)).thenReturn(spellDto);

        SpellDTO result = spellService.getSpellById(1L);

        assertNotNull(result);
        assertEquals(spellDto, result);

        verify(spellRepositoryMock).findById(1L);
        verify(spellMapperMock).spellToSpellDTO(spell);
    }

    @Test
    void getSpellById_shouldThrowException_whenNotFound() {
        when(spellRepositoryMock.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> spellService.getSpellById(1L));

        assertEquals("Spell not found", exception.getMessage());
        verify(spellRepositoryMock).findById(1L);
        verify(spellMapperMock, never()).spellToSpellDTO(any());
    }

    @Test
    void createSpell_shouldReturnSpellDTO() {
        SpellDTO spellDto = new SpellDTO(1L, "Test Name", "Test Level", "Test CastingTime", "Test Range", false, false, false, "Test Materials", "Test Duration", false, false, "Test School", "Test Description");

        Spell spell = getSpell();
        Spell savedSpell = getSpell();
        savedSpell.setId(5L);

        when(spellMapperMock.spellDTOToSpell(spellDto)).thenReturn(spell);
        when(spellRepositoryMock.save(spell)).thenReturn(savedSpell);
        when(spellMapperMock.spellToSpellDTO(savedSpell)).thenReturn(spellDto);

        SpellDTO result = spellService.createSpell(spellDto);

        assertNotNull(result);
        assertEquals(spellDto, result);

        verify(spellMapperMock).spellDTOToSpell(spellDto);
        verify(spellRepositoryMock).save(spell);
        verify(spellMapperMock).spellToSpellDTO(savedSpell);
    }

    @Test
    void updateSpell_shouldReturnSpellDTO_whenFound() {
        Spell existingSpell = getExistingSpell();

        SpellDTO updateDto = new SpellDTO(null, "New Name", "New Level", "New CastingTime", "New Range", false, false, false, "New Materials", "New Duration", false, false, "New School", "New Description");

        Spell updatedSpell = getUpdatedSpell();

        SpellDTO updatedDto = new SpellDTO(1L, "New Name", "New Level", "New CastingTime", "New Range", false, false, false, "New Materials", "New Duration", false, false, "New School", "New Description");

        when(spellRepositoryMock.findById(1L)).thenReturn(Optional.of(existingSpell));
        when(spellRepositoryMock.save(existingSpell)).thenReturn(updatedSpell);
        when(spellMapperMock.spellToSpellDTO(updatedSpell)).thenReturn(updatedDto);

        SpellDTO result = spellService.updateSpell(1L, updateDto);

        assertNotNull(result);
        assertEquals(updatedDto, result);

        verify(spellRepositoryMock).findById(1L);
        verify(spellRepositoryMock).save(existingSpell);
        verify(spellMapperMock).spellToSpellDTO(updatedSpell);
    }

    @Test
    void updateSpell_shouldThrowException_whenNotFound() {
        SpellDTO updateDto = new SpellDTO(null, "New Name", "New Level", "New CastingTime", "New Range", false, false, false, "New Materials", "New Duration", false, false, "New School", "New Description");

        when(spellRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> spellService.updateSpell(1L, updateDto));

        assertEquals("Spell not found", exception.getMessage());

        verify(spellRepositoryMock).findById(1L);
        verify(spellRepositoryMock, never()).save(any());
        verify(spellMapperMock, never()).spellToSpellDTO(any());
    }

    @Test
    void deleteSpell_shouldCallRepositoryDeleteById() {
        spellService.deleteSpell(1L);

        verify(spellRepositoryMock).deleteById(1L);
    }

    /** ADDs */
    @Test
    @DisplayName("getAllByNameContainingIgnoreCase() should return matching SpellDTOs")
    void getAllByNameContainingIgnoreCase_shouldReturnMatchingSpellDTOs() {
        SpellRepository spellRepository = mock(SpellRepository.class);
        SpellMapper spellMapper = mock(SpellMapper.class);

        spellService = new SpellServiceImpl(spellRepository, spellMapper);

        Spell spell1 = new Spell(
                "Test Name 1",
                "Test Level 1",
                "Test CastingTime 1",
                "Test RangeArea 1",
                false,
                false,
                false,
                "Test ComponentMaterials 1",
                "Test Duration 1",
                false,
                false,
                "Test School 1",
                "Test Description 1"
        );

        Spell spell2 = new Spell(
                "Test Name 2",
                "Test Level 2",
                "Test CastingTime 2",
                "Test RangeArea 2",
                false,
                false,
                false,
                "Test ComponentMaterials 2",
                "Test Duration 2",
                false,
                false,
                "Test School 2",
                "Test Description 2"
        );

        SpellDTO spellDto1 = new SpellDTO();
        spellDto1.setId(1L);
        spellDto1.setName("Test Name 1");
        spellDto1.setLevel("Test Level 1");
        spellDto1.setCastingTime("Test CastingTime 1");
        spellDto1.setRangeArea("Test RangeArea 1");
        spellDto1.setComponentVisual(false);
        spellDto1.setComponentSemantic(false);
        spellDto1.setComponentMaterial(false);
        spellDto1.setComponentMaterials("Test ComponentMaterials 1");
        spellDto1.setDuration("Test Duration 1");
        spellDto1.setConcentration(false);
        spellDto1.setRitual(false);
        spellDto1.setSchool("Test School 1");
        spellDto1.setDescription("Test Description 1");

        SpellDTO spellDto2 = new SpellDTO();
        spellDto2.setId(2L);
        spellDto2.setName("Test Name 2");
        spellDto2.setLevel("Test Level 2");
        spellDto2.setCastingTime("Test CastingTime 2");
        spellDto2.setRangeArea("Test RangeArea 2");
        spellDto2.setComponentVisual(false);
        spellDto2.setComponentSemantic(false);
        spellDto2.setComponentMaterial(false);
        spellDto2.setComponentMaterials("Test ComponentMaterials 2");
        spellDto2.setDuration("Test Duration 2");
        spellDto2.setConcentration(false);
        spellDto2.setRitual(false);
        spellDto2.setSchool("Test School 2");
        spellDto2.setDescription("Test Description 2");

        when(spellRepository.findAllByNameContainingIgnoreCase("Test"))
                .thenReturn(List.of(spell1, spell2));
        when(spellMapper.spellToSpellDTO(spell1)).thenReturn(spellDto1);
        when(spellMapper.spellToSpellDTO(spell2)).thenReturn(spellDto2);

        List<SpellDTO> results = spellService.getAllByNameContainingIgnoreCase("Test");

        assertNotNull(results);
        assertEquals(2, results.size());

        assertEquals("Test Name 1", results.get(0).getName());
        assertEquals("Test Level 1", results.get(0).getLevel());

        assertEquals("Test Name 2", results.get(1).getName());
        assertEquals("Test Level 2", results.get(1).getLevel());

        verify(spellRepository).findAllByNameContainingIgnoreCase("Test");
        verify(spellMapper).spellToSpellDTO(spell1);
        verify(spellMapper).spellToSpellDTO(spell2);
    }

    /** SUPs */
    private static @NonNull Spell getSpell() {
        Spell spell = new Spell();
        spell.setId(1L);
        spell.setName("Test Name");
        spell.setLevel("Test Level");
        spell.setCastingTime("Test CastingTime");
        spell.setRangeArea("Test Range");
        spell.setComponentVisual(false);
        spell.setComponentSemantic(false);
        spell.setComponentMaterial(false);
        spell.setComponentMaterials("Test Materials");
        spell.setDuration("Test Duration");
        spell.setConcentration(false);
        spell.setRitual(false);
        spell.setSchool("Test School");
        spell.setDescription("Test Description");
        return spell;
    }

    private static @NonNull Spell getUpdatedSpell() {
        Spell updatedSpell = new Spell();
        updatedSpell.setId(1L);
        updatedSpell.setName("New Name");
        updatedSpell.setLevel("New Level");
        updatedSpell.setCastingTime("New CastingTime");
        updatedSpell.setRangeArea("New Range");
        updatedSpell.setComponentVisual(false);
        updatedSpell.setComponentSemantic(false);
        updatedSpell.setComponentMaterial(false);
        updatedSpell.setComponentMaterials("New Materials");
        updatedSpell.setDuration("New Duration");
        updatedSpell.setConcentration(false);
        updatedSpell.setRitual(false);
        updatedSpell.setSchool("New School");
        updatedSpell.setDescription("New Description");
        return updatedSpell;
    }

    private static @NonNull Spell getExistingSpell() {
        Spell existingSpell = new Spell();
        existingSpell.setId(1L);
        existingSpell.setName("Old Name");
        existingSpell.setLevel("Old Level");
        existingSpell.setCastingTime("Old CastingTime");
        existingSpell.setRangeArea("Old Range");
        existingSpell.setComponentVisual(false);
        existingSpell.setComponentSemantic(false);
        existingSpell.setComponentMaterial(false);
        existingSpell.setComponentMaterials("Old Materials");
        existingSpell.setDuration("Old Duration");
        existingSpell.setConcentration(false);
        existingSpell.setRitual(false);
        existingSpell.setSchool("Old School");
        existingSpell.setDescription("Old Description");
        return existingSpell;
    }
}

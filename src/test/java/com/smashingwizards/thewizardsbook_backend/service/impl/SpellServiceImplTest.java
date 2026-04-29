package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.SpellDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.SpellMapper;
import com.smashingwizards.thewizardsbook_backend.model.*;
import com.smashingwizards.thewizardsbook_backend.repository.SpellClassRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellRepository;
import com.smashingwizards.thewizardsbook_backend.repository.SpellSourceRepository;
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
    @Mock
    private SpellClassRepository spellClassRepositoryMock;
    @Mock
    private SpellSourceRepository spellSourceRepositoryMock;

    @InjectMocks
    private SpellServiceImpl underTest;

    @Test
    void getAllSpells_shouldReturnLstOfSpellDTOs() {
        Spell spell = getSpell();

        SpellDTO spellDto = getSpellDTO(null);

        when(spellRepositoryMock.findAll()).thenReturn(List.of(spell));
        when(spellMapperMock.spellToSpellDTO(spell)).thenReturn(spellDto);

        List<SpellDTO> result = underTest.getAllSpells();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(spellDto, result.get(0));

        verify(spellRepositoryMock).findAll();
        verify(spellMapperMock).spellToSpellDTO(spell);
    }

    @Test
    void getSpellById_shouldReturnSpellDTO() {
        Spell spell = getSpell();

        SpellDTO spellDto = getSpellDTO(null);

        when(spellRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(spell));
        when(spellMapperMock.spellToSpellDTO(spell)).thenReturn(spellDto);

        SpellDTO result = underTest.getSpellById(1L);

        assertNotNull(result);
        assertEquals(spellDto, result);

        verify(spellRepositoryMock).findById(1L);
        verify(spellMapperMock).spellToSpellDTO(spell);
    }

    @Test
    void getSpellById_shouldThrowException_whenNotFound() {
        when(spellRepositoryMock.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> underTest.getSpellById(1L));

        assertEquals("Spell not found", exception.getMessage());
        verify(spellRepositoryMock).findById(1L);
        verify(spellMapperMock, never()).spellToSpellDTO(any());
    }

    @Test
    void createSpell_shouldReturnSpellDTO() {
        SpellDTO spellDto = getSpellDTO(null);

        Spell spell = getSpell();
        Spell savedSpell = getSpell();
        savedSpell.setId(5L);

        when(spellMapperMock.spellDTOToSpell(spellDto)).thenReturn(spell);
        when(spellRepositoryMock.save(spell)).thenReturn(savedSpell);
        when(spellMapperMock.spellToSpellDTO(savedSpell)).thenReturn(spellDto);

        SpellDTO result = underTest.createSpell(spellDto);

        assertNotNull(result);
        assertEquals(spellDto, result);

        verify(spellMapperMock).spellDTOToSpell(spellDto);
        verify(spellRepositoryMock).save(spell);
        verify(spellMapperMock).spellToSpellDTO(savedSpell);
    }

    @Test
    void updateSpell_shouldReturnSpellDTO_whenFound() {
        Spell existingSpell = getOldSpell();
        SpellDTO updateDto = getNewSpellDTO(1L);
        Spell updatedSpell = getUpdatedSpell();
        SpellDTO updatedDto = getOldSpellDTO(1L);

        when(spellRepositoryMock.findById(1L)).thenReturn(Optional.of(existingSpell));
        when(spellRepositoryMock.save(existingSpell)).thenReturn(updatedSpell);
        when(spellMapperMock.spellToSpellDTO(updatedSpell)).thenReturn(updatedDto);

        SpellDTO result = underTest.updateSpell(1L, updateDto);

        assertNotNull(result);
        assertEquals(updatedDto, result);

        verify(spellRepositoryMock).findById(1L);
        verify(spellRepositoryMock).save(existingSpell);
        verify(spellMapperMock).spellToSpellDTO(updatedSpell);
    }

    @Test
    void updateSpell_shouldThrowException_whenNotFound() {
        SpellDTO updateDto = getNewSpellDTO(1L);

        when(spellRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> underTest.updateSpell(1L, updateDto));

        assertEquals("Spell not found", exception.getMessage());

        verify(spellRepositoryMock).findById(1L);
        verify(spellRepositoryMock, never()).save(any());
        verify(spellMapperMock, never()).spellToSpellDTO(any());
    }

    @Test
    void deleteSpell_shouldCallRepositoryDeleteById() {
        underTest.deleteSpell(1L);
        verify(spellRepositoryMock).deleteById(1L);
    }

    /** ADDs */
    @Test
    @DisplayName("getAllByNameContainingIgnoreCase() should return matching SpellDTOs")
    void getAllByNameContainingIgnoreCase_shouldReturnMatchingSpellDTOs() {
        Spell spell1 = getSpell(1L);
        Spell spell2 = getSpell(2L);

        SpellDTO spellDto1 = getSpellDTO(1L);
        SpellDTO spellDto2 = getSpellDTO(2L);

        when(spellRepositoryMock.findAllByNameContainingIgnoreCase("Test"))
                .thenReturn(List.of(spell1, spell2));
        when(spellMapperMock.spellToSpellDTO(spell1)).thenReturn(spellDto1);
        when(spellMapperMock.spellToSpellDTO(spell2)).thenReturn(spellDto2);

        List<SpellDTO> results = underTest.getAllByNameContainingIgnoreCase("Test");

        assertNotNull(results);
        assertEquals(2, results.size());

        assertEquals("Test Spell Name 1", results.get(0).getName());
        assertEquals("Test Level", results.get(0).getLevel());

        assertEquals("Test Spell Name 2", results.get(1).getName());
        assertEquals("Test Level", results.get(1).getLevel());

        verify(spellRepositoryMock).findAllByNameContainingIgnoreCase("Test");
        verify(spellMapperMock).spellToSpellDTO(spell1);
        verify(spellMapperMock).spellToSpellDTO(spell2);
    }

    @Test
    @DisplayName("getAllByRpgClassId() should return matching rpgClass name")
    void getAllSpellsContainingIgnoreCase_shouldReturnMatchingRpgClassName(){
        Spell spell1 = getSpell(1L);
        Spell spell2 = getSpell(2L);

        RpgClass rpgClass = new RpgClass("Test Class", "Test SubClass", "Test Description");

        SpellClass spellClass1 = new SpellClass(spell1, rpgClass);
        SpellClass spellClass2 = new SpellClass(spell2, rpgClass);

        SpellDTO spellDto1 = new SpellDTO();
        spellDto1.setName("Test Spell Name 1");

        SpellDTO spellDto2 = new SpellDTO();
        spellDto2.setName("Test Spell Name 2");

        when(spellClassRepositoryMock.findAllByRpgClass_NameContainingIgnoreCase("Test")).thenReturn(List.of(spellClass1, spellClass2));
        when(spellMapperMock.spellToSpellDTO(spell1)).thenReturn(spellDto1);
        when(spellMapperMock.spellToSpellDTO(spell2)).thenReturn(spellDto2);

        List<SpellDTO> results = underTest.getAllByRpgClassNameContainingIgnoreCase("Test");

        assertEquals(2, results.size());
        assertEquals("Test Spell Name 1", results.get(0).getName());
        assertEquals("Test Spell Name 2", results.get(1).getName());

        verify(spellClassRepositoryMock).findAllByRpgClass_NameContainingIgnoreCase("Test");
        verify(spellMapperMock).spellToSpellDTO(spell1);
        verify(spellMapperMock).spellToSpellDTO(spell2);
    }


    @Test
    @DisplayName("getAllBySourceId() should return matching source name")
    void getAllSpellsContainingIgnoreCase_shouldReturnMatchingSourceName(){
        Spell spell1 = getSpell(1L);
        Spell spell2 = getSpell(2L);

        Source source = new Source("Test Source Name", "Test Source SubClass", "Test Source Description");

        SpellSource spellSource1 = new SpellSource(spell1, source, "pg 1");
        SpellSource spellSource2 = new SpellSource(spell2, source, "pg 2");

        SpellDTO spellDto1 = new SpellDTO();
        spellDto1.setName("Test Spell Name 1");

        SpellDTO spellDto2 = new SpellDTO();
        spellDto2.setName("Test Spell Name 2");

        when(spellSourceRepositoryMock.findAllBySource_NameContainingIgnoreCase("Test")).thenReturn(List.of(spellSource1, spellSource2));
        when(spellMapperMock.spellToSpellDTO(spell1)).thenReturn(spellDto1);
        when(spellMapperMock.spellToSpellDTO(spell2)).thenReturn(spellDto2);

        List<SpellDTO> results = underTest.getAllBySourceNameContainingIgnoreCase("Test");

        assertEquals(2, results.size());
        assertEquals("Test Spell Name 1", results.get(0).getName());
        assertEquals("Test Spell Name 2", results.get(1).getName());

        verify(spellSourceRepositoryMock).findAllBySource_NameContainingIgnoreCase("Test");
        verify(spellMapperMock).spellToSpellDTO(spell1);
        verify(spellMapperMock).spellToSpellDTO(spell2);
    }

    /** ===============================================================================
     * SUPs
     * */
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

    private static @NonNull Spell getSpell(Long num) {
        Spell spell = new Spell();
        spell.setId(num);
        spell.setName("Test Name " +  num);
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

    private static @NonNull Spell getOldSpell() {
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

    private static @NonNull SpellDTO getSpellDTO(Long num) {
        SpellDTO spellDto = new SpellDTO();
        spellDto.setId(num);
        spellDto.setName("Test Spell Name " + num);
        spellDto.setLevel("Test Level");
        spellDto.setCastingTime("Test CastingTime");
        spellDto.setRangeArea("Test Range");
        spellDto.setComponentVisual(false);
        spellDto.setComponentSemantic(false);
        spellDto.setComponentMaterial(false);
        spellDto.setComponentMaterials("Test Materials");
        spellDto.setDuration("Test Duration");
        spellDto.setConcentration(false);
        spellDto.setRitual(false);
        spellDto.setSchool("Test School");
        spellDto.setDescription("Test Description");
        return spellDto;
    }

    private static @NonNull SpellDTO getNewSpellDTO(Long num) {
        SpellDTO spellDto = new SpellDTO();
        spellDto.setId(num);
        spellDto.setName("New Name");
        spellDto.setLevel("New Level");
        spellDto.setCastingTime("New CastingTime");
        spellDto.setRangeArea("New Range");
        spellDto.setComponentVisual(false);
        spellDto.setComponentSemantic(false);
        spellDto.setComponentMaterial(false);
        spellDto.setComponentMaterials("New Materials");
        spellDto.setDuration("New Duration");
        spellDto.setConcentration(false);
        spellDto.setRitual(false);
        spellDto.setSchool("New School");
        spellDto.setDescription("New Description");
        return spellDto;
    }

    private static @NonNull SpellDTO getOldSpellDTO(Long num) {
        SpellDTO spellDto = new SpellDTO();
        spellDto.setId(num);
        spellDto.setName("Old Name");
        spellDto.setLevel("Old Level");
        spellDto.setCastingTime("Old CastingTime");
        spellDto.setRangeArea("Old Range");
        spellDto.setComponentVisual(false);
        spellDto.setComponentSemantic(false);
        spellDto.setComponentMaterial(false);
        spellDto.setComponentMaterials("Old Materials");
        spellDto.setDuration("Old Duration");
        spellDto.setConcentration(false);
        spellDto.setRitual(false);
        spellDto.setSchool("Old School");
        spellDto.setDescription("Old Description");
        return spellDto;
    }
}

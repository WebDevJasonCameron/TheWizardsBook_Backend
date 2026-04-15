package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.TypeDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.TypeMapper;
import com.smashingwizards.thewizardsbook_backend.model.Type;
import com.smashingwizards.thewizardsbook_backend.repository.TypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TypeServiceImplTest {

    @Mock
    private TypeRepository typeRepositoryMock;
    @Mock
    private TypeMapper typeMapperMock;

    @InjectMocks
    private TypeServiceImpl typeServiceImpl;

    @Test
    void getAllTypes_shouldReturnLstOfTypesDTOs() {
        Type type = new Type();
        type.setId(1L);
        type.setName("Test Type");
        type.setSubType("Test SubType");

        TypeDTO dto = new TypeDTO(1L, "Test Type", "Test SubType");

        when(typeRepositoryMock.findAll()).thenReturn(List.of(type));
        when(typeMapperMock.typeToTypeDTO(type)).thenReturn(dto);

        List<TypeDTO> result = typeServiceImpl.getAllTypes();

        assertNotNull(result);
        assertEquals(1L, result.size());
        assertEquals("Test Type", result.get(0).getName());
        assertEquals("Test SubType", result.get(0).getSubType());

        verify(typeRepositoryMock).findAll();
        verify(typeMapperMock).typeToTypeDTO(type);
    }

    @Test
    void getTypeById_shouldReturnTypeDTO() {
        Type type = new Type();
        type.setId(1L);
        type.setName("Test Type");
        type.setSubType("Test SubType");

        TypeDTO dto = new TypeDTO(1L, "Test Type", "Test SubType");

        when(typeRepositoryMock.findById(1L)).thenReturn(Optional.of(type));
        when(typeMapperMock.typeToTypeDTO(type)).thenReturn(dto);

        TypeDTO result = typeServiceImpl.getTypeById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Type", result.getName());
        assertEquals("Test SubType", result.getSubType());

        verify(typeRepositoryMock).findById(1L);
        verify(typeMapperMock).typeToTypeDTO(type);
    }

    @Test
    void getTypeById_shouldThrowException_whenNotFound() {
        when(typeRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> typeServiceImpl.getTypeById(1L));

        assertEquals("Type not found", exception.getMessage());
        verify(typeRepositoryMock).findById(1L);
        verify(typeMapperMock, never()).typeToTypeDTO(any());
    }

    @Test
    void createType_shouldSaveAndReturnTypeDTO() {
        TypeDTO dto = new TypeDTO(null, "Test Type", "Test SubType");
        Type type = new Type();
        type.setName("Test Type");
        type.setSubType("Test SubType");

        Type savedType = new Type();
        savedType.setId(5L);
        savedType.setName("Test Type");
        savedType.setSubType("Test SubType");

        TypeDTO savedDto = new TypeDTO(5L, "Test Type", "Test SubType");

        when(typeMapperMock.typeDTOToType(dto)).thenReturn(type);
        when(typeRepositoryMock.save(type)).thenReturn(savedType);
        when(typeMapperMock.typeToTypeDTO(savedType)).thenReturn(savedDto);

        TypeDTO result = typeServiceImpl.createType(dto);

        assertNotNull(result);
        assertEquals(5L, result.getId());
        assertEquals("Test Type", result.getName());
        assertEquals("Test SubType", result.getSubType());

        verify(typeMapperMock).typeDTOToType(dto);
        verify(typeRepositoryMock).save(type);
        verify(typeMapperMock).typeToTypeDTO(savedType);
    }

    @Test
    void updateType_shouldUpdateAndReturnTypeDTO_whenFound() {
        Type existingType = new Type();
        existingType.setId(1L);
        existingType.setName("Old Name");
        existingType.setSubType("Old SubType");

        TypeDTO updateDto = new TypeDTO(null, "New Name", "New SubType");

        Type updatedType = new Type();
        updatedType.setId(1L);
        updatedType.setName("New Name");
        updatedType.setSubType("New SubType");

        TypeDTO updatedDto = new TypeDTO(1L, "New Name", "New SubType");

        when(typeRepositoryMock.findById(1L)).thenReturn(Optional.of(existingType));
        when(typeRepositoryMock.save(existingType)).thenReturn(updatedType);
        when(typeMapperMock.typeToTypeDTO(updatedType)).thenReturn(updatedDto);

        TypeDTO result = typeServiceImpl.updateType(1L, updateDto);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("New SubType", result.getSubType());

        verify(typeRepositoryMock).findById(1L);
        verify(typeRepositoryMock).save(existingType);
        verify(typeMapperMock).typeToTypeDTO(updatedType);
    }

    @Test
    void updateType_shouldThrowException_whenNotFound() {
        TypeDTO updateDto = new TypeDTO(null, "New name", "New SubTye");

        when(typeRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                typeServiceImpl.updateType(1L, updateDto)
        );

        assertEquals("Type not found", exception.getMessage());

        Mockito.verify(typeRepositoryMock).findById(1L);
        Mockito.verify(typeRepositoryMock, never()).save(any());
    }

    @Test
    void deleteType_shouldCallRepositoryDeleteById() {
        typeServiceImpl.deleteType(1L);

        Mockito.verify(typeRepositoryMock).deleteById(1L);
    }
}

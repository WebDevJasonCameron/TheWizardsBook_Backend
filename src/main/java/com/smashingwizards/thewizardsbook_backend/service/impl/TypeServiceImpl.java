package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.TypeDTO;
import com.smashingwizards.thewizardsbook_backend.mapper.TypeMapper;
import com.smashingwizards.thewizardsbook_backend.model.Type;
import com.smashingwizards.thewizardsbook_backend.repository.TypeRepository;
import com.smashingwizards.thewizardsbook_backend.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    // ATTs
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    // CONs
    public TypeServiceImpl(TypeRepository typeRepository, TypeMapper typeMapper) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
    }

    // CRUDs
    @Override
    public List<TypeDTO> getTypes() {
        return typeRepository.findAll()
                .stream()
                .map(typeMapper::typeToTypeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TypeDTO getTypeById(Long id) {
        return typeRepository.findById(id)
                .map(typeMapper::typeToTypeDTO)
                .orElseThrow(() -> new RuntimeException("Type not found"));
    }

    @Override
    public TypeDTO createType(TypeDTO typeDTO) {
        return typeMapper.typeToTypeDTO(typeRepository.save(typeMapper.typeDTOToType(typeDTO)));
    }

    @Override
    public TypeDTO updateType(Long id, TypeDTO typeDTO) {
        Optional<Type> optionalType = typeRepository.findById(id);
        if(!optionalType.isPresent()) {
            throw new RuntimeException("Type not found");
        }
        Type existingType = optionalType.get();

        existingType.setName(optionalType.get().getName());
        existingType.setSubType(optionalType.get().getSubType());

        return typeMapper.typeToTypeDTO(typeRepository.save(existingType));
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }




}

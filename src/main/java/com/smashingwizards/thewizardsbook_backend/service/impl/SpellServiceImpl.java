package com.smashingwizards.thewizardsbook_backend.service.impl;

import com.smashingwizards.thewizardsbook_backend.dto.*;
import com.smashingwizards.thewizardsbook_backend.mapper.*;
import com.smashingwizards.thewizardsbook_backend.model.*;
import com.smashingwizards.thewizardsbook_backend.repository.*;
import com.smashingwizards.thewizardsbook_backend.service.SpellService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpellServiceImpl implements SpellService {

    private SpellRepository spellRepository;
    private final SpellClassRepository spellClassRepository;
    private final SpellSourceRepository spellSourceRepository;
    private final SpellTagRepository spellTagRepository;
    private final SpellTtrpgRepository spellTtrpgRepository;

    private final RpgClassRepository rpgClassRepository;
    private final TagRepository tagRepository;
    private final SourceRepository sourceRepository;
    private final TtrpgRepository ttrpgRepository;

    private final RpgClassMapper rpgClassMapper;
    private final TagMapper tagMapper;
    private final SourceMapper sourceMapper;
    private final TtrpgMapper ttrpgMapper;
    public SpellMapper spellMapper;

    // CONs
    public SpellServiceImpl(SpellRepository spellRepository, SpellClassRepository spellClassRepository, SpellSourceRepository spellSourceRepository, SpellTagRepository spellTagRepository,  SpellTtrpgRepository spellTtrpgRepository, RpgClassRepository rpgClassRepository, TagRepository tagRepository, SourceRepository sourceRepository, TtrpgRepository ttrpgRepository, RpgClassMapper rpgClassMapper, TagMapper tagMapper, SourceMapper sourceMapper, TtrpgMapper ttrpgMapper, SpellMapper spellMapper) {
        this.spellRepository = spellRepository;
        this.spellClassRepository = spellClassRepository;
        this.spellSourceRepository = spellSourceRepository;
        this.spellTagRepository = spellTagRepository;
        this.spellTtrpgRepository = spellTtrpgRepository;
        this.rpgClassRepository = rpgClassRepository;
        this.tagRepository = tagRepository;
        this.sourceRepository = sourceRepository;
        this.ttrpgRepository = ttrpgRepository;

        this.rpgClassMapper = rpgClassMapper;
        this.tagMapper = tagMapper;
        this.sourceMapper = sourceMapper;
        this.ttrpgMapper = ttrpgMapper;
        this.spellMapper = spellMapper;
    }

    // CRUDs
    @Override
    public List<SpellDTO> getAllSpells() {
        return spellRepository.findAll().stream()
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public SpellDTO getSpellById(Long id) {
        return spellRepository.findById(id)
                .map(spellMapper::spellToSpellDTO)
                .orElseThrow(() -> new RuntimeException("Spell not found"));
    }

    @Override
    @Transactional
    public SpellDTO createSpell(SpellDTO spellDTO) {
        return spellMapper.spellToSpellDTO(spellRepository
                .save(spellMapper.spellDTOToSpell(spellDTO)));
    }

    @Override
    @Transactional
    public SpellDTO updateSpell(Long id, SpellDTO spellDTO) {
        Optional<Spell> optionalSpell = spellRepository.findById(id);
        if (!optionalSpell.isPresent()) {
            throw new RuntimeException("Spell not found");
        }
        Spell existingSpell = optionalSpell.get();

        existingSpell.setName(spellDTO.getName());
        existingSpell.setLevel(spellDTO.getLevel());
        existingSpell.setCastingTime(spellDTO.getCastingTime());
        existingSpell.setRangeArea(spellDTO.getRangeArea());
        existingSpell.setComponentVisual(spellDTO.isComponentVisual());
        existingSpell.setComponentSemantic(spellDTO.isComponentSemantic());
        existingSpell.setComponentMaterial(spellDTO.isComponentMaterial());
        existingSpell.setComponentMaterials(spellDTO.getComponentMaterials());
        existingSpell.setDuration(spellDTO.getDuration());
        existingSpell.setConcentration(spellDTO.isConcentration());
        existingSpell.setRitual(spellDTO.isRitual());
        existingSpell.setSchool(spellDTO.getSchool());
        existingSpell.setDescription(spellDTO.getDescription());

        return spellMapper.spellToSpellDTO(spellRepository.save(existingSpell));
    }

    @Override
    public void deleteSpell(Long id) {
        spellRepository.deleteById(id);
    }

    /** ADDs */
    @Override
    public List<SpellDTO> getAllByNameContainingIgnoreCase(String name) {
        return spellRepository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllByRpgClassNameContainingIgnoreCase(String name) {
        return spellClassRepository.findAllByRpgClass_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellClass::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllBySourceNameContainingIgnoreCase(String name) {
        return spellSourceRepository.findAllBySource_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellSource::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllByTagNameContainingIgnoreCase(String name) {
        return spellTagRepository.findAllByTag_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellTag::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public List<SpellDTO> getAllByTtrpgNameContainingIgnoreCase(String name) {
        return spellTtrpgRepository.findAllByTtrpg_NameContainingIgnoreCase(name)
                .stream()
                .map(SpellTtrpg::getSpell)
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    public SpellDetailsDTO getSpellDetailsById(Long id) {
        Spell spell = spellRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Spell not found"));

        SpellDetailsDTO dto = new SpellDetailsDTO();

        dto.setSpell(spellMapper.spellToSpellDTO(spell));

        dto.setRpgClasses(
                spellClassRepository.findAllBySpellId(id)
                        .stream()
                        .map(SpellClass::getRpgClass)
                        .map(rpgClassMapper::rpgClassToRpgClassDTO)
                        .toList()
        );

        dto.setTags(
                spellTagRepository.findAllBySpellId(id)
                        .stream()
                        .map(SpellTag::getTag)
                        .map(tagMapper::tagToTagDTO)
                        .toList()
        );

        dto.setSources(
                spellSourceRepository.findAllBySpellId(id)
                        .stream()
                        .map(SpellSource::getSource)
                        .map(sourceMapper::sourceToSourceDTO)
                        .toList()
        );

        dto.setTtrpgs(
                spellTtrpgRepository.findAllBySpellId(id)
                        .stream()
                        .map(SpellTtrpg::getTtrpg)
                        .map(ttrpgMapper::ttrpgToTtrpgDTO)
                        .toList()
        );
        return dto;
    }

    @Override
    public List<SpellDTO> searchSpells(
            String name,
            List<String> levels,
            Boolean concentration,
            Boolean ritual,
            Boolean componentVisual,
            Boolean componentSemantic,
            Boolean componentMaterial,
            List<Long> ttrpgIds,
            List<Long> classIds,
            List<Long> sourceIds,
            List<Long> tagIds
    ) {
        String cleanedName = (name == null || name.isBlank()) ? null : name.trim();

        boolean levelsEmpty = levels == null;
        boolean concentrationEmpty = concentration == null;
        boolean ritualEmpty = ritual == null;
        boolean componentVisualEmpty = componentVisual == null;
        boolean componentSemanticEmpty = componentSemantic == null;
        boolean componentMaterialEmpty = componentMaterial == null;
        boolean ttrpgIdsEmpty = ttrpgIds == null;
        boolean classIdsEmpty = classIds == null;
        boolean sourceIdsEmpty = sourceIds == null;
        boolean tagIdsEmpty = tagIds == null;

        List<String> safeLevels = levelsEmpty ? List.of("__NONE__") : levels;
        List<Boolean> safeConcentration = concentrationEmpty ? List.of(false) : List.of(concentration);
        List<Boolean> safeRitual = ritualEmpty ? List.of(false) : List.of(ritual);
        List<Boolean> safeComponentVisual = componentVisualEmpty ? List.of(false) : List.of(componentVisual);
        List<Boolean> safeComponentSemantic = componentSemanticEmpty ? List.of(false) : List.of(componentSemantic);
        List<Boolean> safeComponentMaterial = componentMaterialEmpty ? List.of(false) : List.of(componentMaterial);
        List<Long> safeTtrpgIds = ttrpgIdsEmpty ? List.of(-1L) : ttrpgIds;
        List<Long> safeClassIds = classIdsEmpty ? List.of(-1L) : classIds;
        List<Long> safeSourceIds = sourceIdsEmpty ? List.of(-1L) : sourceIds;
        List<Long> safeTagIds = tagIdsEmpty ? List.of(-1L) : tagIds;

        return spellRepository.searchSpells(
                        cleanedName,
                        safeLevels,
                        levelsEmpty,
                        concentration,
                        concentrationEmpty,
                        ritual,
                        ritualEmpty,
                        componentVisual,
                        componentVisualEmpty,
                        componentSemantic,
                        componentSemanticEmpty,
                        componentMaterial,
                        componentMaterialEmpty,
                        safeClassIds,
                        classIdsEmpty,
                        safeTtrpgIds,
                        ttrpgIdsEmpty,
                        safeSourceIds,
                        sourceIdsEmpty,
                        safeTagIds,
                        tagIdsEmpty
                )
                .stream()
                .map(spellMapper::spellToSpellDTO)
                .toList();
    }

    @Override
    @Transactional
    public SpellDetailsDTO createSpellWithDetails(CreateSpellRequestDTO createSpellRequestDTO) {
        Spell spell = spellMapper.spellDTOToSpell(createSpellRequestDTO.getSpell());
        Spell savedSpell = spellRepository.save(spell);

        for (Long classId : createSpellRequestDTO.getRpgClassIds()) {
            RpgClass rpgClass = rpgClassRepository.getReferenceById(classId);
            spellClassRepository.save(new SpellClass(savedSpell, rpgClass));
        }

        for (Long tagId : createSpellRequestDTO.getTagIds()) {
            Tag tag = tagRepository.getReferenceById(tagId);
            spellTagRepository.save(new SpellTag(savedSpell, tag));
        }

        for (SourceAssignmentDTO sourceAssignment : createSpellRequestDTO.getSources()) {
            Source source = sourceRepository.getReferenceById(sourceAssignment.getSourceId());

            spellSourceRepository.save(
                    new SpellSource(savedSpell, source, sourceAssignment.getPage())
            );
        }

        for (Long ttrpgId : createSpellRequestDTO.getTtrpgIds()) {
            Ttrpg ttrpg = ttrpgRepository.getReferenceById(ttrpgId);
            spellTtrpgRepository.save(new SpellTtrpg(savedSpell, ttrpg));
        }

        return getSpellDetailsById(savedSpell.getId());
    }
}

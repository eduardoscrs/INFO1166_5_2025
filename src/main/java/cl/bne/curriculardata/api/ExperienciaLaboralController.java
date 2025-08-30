package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.api.dto.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ExperienciaLaboralController {

    // store en memoria para el MVP
    private final Map<Long, ExperienciaLaboral> store = new HashMap<>();
    private long idCounter = 1;

    // Crear y asociar al postulante
    @PostMapping("/personas/{postulanteId}/experiencias")
    @ResponseStatus(HttpStatus.CREATED)
    public ExperienciaLaboralDTO crear(@PathVariable Long postulanteId,
                                       @Valid @RequestBody ExperienciaLaboralCreateDTO dto) {
        ExperienciaLaboral m = new ExperienciaLaboral();
        m.setId(idCounter++);
        m.setPostulanteId(postulanteId);
        m.setEmpresa(dto.getEmpresa());
        m.setCargo(dto.getCargo());
        m.setDescripcion(dto.getDescripcion());
        m.setAnios(dto.getAnios() == null ? 0 : dto.getAnios());
        validar(m);
        store.put(m.getId(), m);
        return toDTO(m);
    }

    // Listar por postulante
    @GetMapping("/personas/{postulanteId}/experiencias")
    public List<ExperienciaLaboralDTO> listar(@PathVariable Long postulanteId) {
        return store.values().stream()
                .filter(e -> Objects.equals(e.getPostulanteId(), postulanteId))
                .sorted(Comparator.comparing(ExperienciaLaboral::getId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener por id
    @GetMapping("/experiencias/{id}")
    public ExperienciaLaboralDTO obtener(@PathVariable Long id) {
        return toDTO(require(id));
    }

    // Editar (no cambia la asociación)
    @PutMapping("/experiencias/{id}")
    public ExperienciaLaboralDTO editar(@PathVariable Long id,
                                        @Valid @RequestBody ExperienciaLaboralUpdateDTO dto) {
        ExperienciaLaboral m = require(id);
        m.setEmpresa(dto.getEmpresa());
        m.setCargo(dto.getCargo());
        m.setDescripcion(dto.getDescripcion());
        m.setAnios(dto.getAnios() == null ? 0 : dto.getAnios());
        validar(m);
        return toDTO(m);
    }

    // Eliminar
    @DeleteMapping("/experiencias/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        if (store.remove(id) == null) throw notFound(id);
    }

    // Asociar existente a un postulante
    @PostMapping("/personas/{postulanteId}/experiencias/{id}/asociar")
    public ExperienciaLaboralDTO asociar(@PathVariable Long postulanteId, @PathVariable Long id) {
        ExperienciaLaboral m = require(id);
        m.setPostulanteId(postulanteId);
        return toDTO(m);
    }

    // Desasociar de un postulante
    @DeleteMapping("/personas/{postulanteId}/experiencias/{id}/asociacion")
    public ExperienciaLaboralDTO desasociar(@PathVariable Long postulanteId, @PathVariable Long id) {
        ExperienciaLaboral m = require(id);
        if (!Objects.equals(m.getPostulanteId(), postulanteId)) {
            throw new IllegalArgumentException("La experiencia no está asociada a ese postulante.");
        }
        m.setPostulanteId(null);
        return toDTO(m);
    }

    // -------- helpers --------
    private ExperienciaLaboral require(Long id) {
        ExperienciaLaboral m = store.get(id);
        if (m == null) throw notFound(id);
        return m;
    }

    private RuntimeException notFound(Long id) {
        return new NoSuchElementException("Experiencia no encontrada: " + id);
    }

    private void validar(ExperienciaLaboral m) {
        if (m.getAnios() != null && m.getAnios() < 0)
            throw new IllegalArgumentException("anios no puede ser negativo.");
        if (isBlank(m.getEmpresa()) || isBlank(m.getCargo()))
            throw new IllegalArgumentException("empresa y cargo son obligatorios.");
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }

    private ExperienciaLaboralDTO toDTO(ExperienciaLaboral m) {
        ExperienciaLaboralDTO dto = new ExperienciaLaboralDTO();
        dto.setId(m.getId());
        dto.setPostulanteId(m.getPostulanteId());
        dto.setEmpresa(m.getEmpresa());
        dto.setCargo(m.getCargo());
        dto.setDescripcion(m.getDescripcion());
        dto.setAnios(m.getAnios());
        return dto;
    }
}

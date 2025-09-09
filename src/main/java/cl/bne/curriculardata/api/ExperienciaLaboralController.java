package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.dto.DTOExperienciaLaboral;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/personas/{postulanteId}/experiencias")
public class ExperienciaLaboralController {

    private final Map<Long, ExperienciaLaboral> experiencias = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    private DTOExperienciaLaboral toDto(ExperienciaLaboral e) {
        return new DTOExperienciaLaboral(e.getId(), e.getEmpresa(), e.getCargo(), e.getAnios());
    }

    private ExperienciaLaboral toEntity(DTOExperienciaLaboral d) {
        return new ExperienciaLaboral(d.getId(), null, d.getEmpresa(), d.getCargo(), null, d.getAnios());
    }

    @GetMapping
    public List<DTOExperienciaLaboral> listar(@PathVariable Long postulanteId) {
        // Si quieres filtrar por postulanteId, aquí deberías hacerlo
        return experiencias.values().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOExperienciaLaboral> obtener(@PathVariable Long id, @PathVariable Long postulanteId) {
        var e = experiencias.get(id);
        return (e == null) ? ResponseEntity.notFound().build()
                           : ResponseEntity.ok(toDto(e));
    }

    @PostMapping
    public ResponseEntity<DTOExperienciaLaboral> crear(@PathVariable Long postulanteId, @Valid @RequestBody DTOExperienciaLaboral dto) {
        var entity = toEntity(dto);
        entity.setId(idCounter.getAndIncrement());
        entity.setPostulanteId(postulanteId); // Asocia la experiencia al postulante
        experiencias.put(entity.getId(), entity);

        var out = toDto(entity);
        return ResponseEntity
                .created(URI.create("/api/v1/personas/" + postulanteId + "/experiencias/" + out.getId()))
                .body(out);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOExperienciaLaboral> editar(
            @PathVariable Long id,
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTOExperienciaLaboral dto) {

        var current = experiencias.get(id);
        if (current == null) return ResponseEntity.notFound().build();

        current.setEmpresa(dto.getEmpresa());
        current.setCargo(dto.getCargo());
        current.setAnios(dto.getAnios());
        // Si quieres actualizar el postulanteId, puedes hacerlo aquí
        return ResponseEntity.ok(toDto(current));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @PathVariable Long postulanteId) {
        var removed = experiencias.remove(id);
        return (removed == null) ? ResponseEntity.notFound().build()
                                 : ResponseEntity.noContent().build();
    }
}
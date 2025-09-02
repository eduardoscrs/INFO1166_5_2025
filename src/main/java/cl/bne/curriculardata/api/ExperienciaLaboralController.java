package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.domain.DTOExperiencia.DTOExperienciaLaboral;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaLaboralController {

    // almacenamiento en memoria (thread-safe)
    private final Map<Long, ExperienciaLaboral> experiencias = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Mapper (simple)
    private DTOExperienciaLaboral toDto(ExperienciaLaboral e) {
        return new DTOExperienciaLaboral(e.getId(), e.getEmpresa(), e.getCargo(), e.getAnios());
    }
    private ExperienciaLaboral toEntity(DTOExperienciaLaboral d) {
        return new ExperienciaLaboral(d.getId(), d.getEmpresa(), d.getCargo(), null, d.getAnios());
    }

    // GET: listar todas
    @GetMapping
    public List<DTOExperienciaLaboral> listar() {
        return experiencias.values().stream().map(this::toDto).toList();
    }

    // GET: obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<DTOExperienciaLaboral> obtener(@PathVariable Long id) {
        var e = experiencias.get(id);
        return (e == null) ? ResponseEntity.notFound().build()
                           : ResponseEntity.ok(toDto(e));
    }

    // POST: crear (con validación del DTO)
    @PostMapping
    public ResponseEntity<DTOExperienciaLaboral> crear(@Valid @RequestBody DTOExperienciaLaboral dto) {
        var entity = toEntity(dto);
        entity.setId(idCounter.getAndIncrement());
        experiencias.put(entity.getId(), entity);

        var out = toDto(entity);
        return ResponseEntity
                .created(URI.create("/api/experiencias/" + out.getId())) // 201 + Location
                .body(out);
    }

    // PUT: editar (404 si no existe)
    @PutMapping("/{id}")
    public ResponseEntity<DTOExperienciaLaboral> editar(
            @PathVariable Long id,
            @Valid @RequestBody DTOExperienciaLaboral dto) {

        var current = experiencias.get(id);
        if (current == null) return ResponseEntity.notFound().build();

        current.setEmpresa(dto.getEmpresa());
        current.setCargo(dto.getCargo());
        current.setAnios(dto.getAnios());
        return ResponseEntity.ok(toDto(current));
    }

    // DELETE: eliminar (404 si no existe)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var removed = experiencias.remove(id);
        return (removed == null) ? ResponseEntity.notFound().build()
                                 : ResponseEntity.noContent().build(); // 204
    }
}

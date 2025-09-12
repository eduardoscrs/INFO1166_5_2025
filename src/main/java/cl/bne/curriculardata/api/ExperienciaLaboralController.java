package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.repository.ExperienciaLaboralRepository;
import cl.bne.curriculardata.repository.PostulanteRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/personas/{postulanteId}/experiencias")
public class ExperienciaLaboralController {

    private final ExperienciaLaboralRepository experienciaRepo;
    private final PostulanteRepository postulanteRepo;

    public ExperienciaLaboralController(ExperienciaLaboralRepository experienciaRepo, PostulanteRepository postulanteRepo) {
        this.experienciaRepo = experienciaRepo;
        this.postulanteRepo = postulanteRepo;
    }

    @GetMapping
    public List<ExperienciaLaboral> listar(@PathVariable Long postulanteId) {
        Postulante postulante = postulanteRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));
        return postulante.getExperiencias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaLaboral> obtener(@PathVariable Long postulanteId, @PathVariable Long id) {
        ExperienciaLaboral exp = experienciaRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        if (!exp.getPostulante().getId().equals(postulanteId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exp);
    }

    @PostMapping
    public ResponseEntity<ExperienciaLaboral> crear(@PathVariable Long postulanteId, @Valid @RequestBody ExperienciaLaboral exp) {
        Postulante postulante = postulanteRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));
        exp.setPostulante(postulante);
        ExperienciaLaboral saved = experienciaRepo.save(exp);
        return ResponseEntity
            .created(URI.create("/api/v1/personas/" + postulanteId + "/experiencias/" + saved.getId()))
            .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienciaLaboral> editar(@PathVariable Long postulanteId, @PathVariable Long id, @Valid @RequestBody ExperienciaLaboral exp) {
        ExperienciaLaboral current = experienciaRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        if (!current.getPostulante().getId().equals(postulanteId)) {
            return ResponseEntity.notFound().build();
        }
        current.setEmpresa(exp.getEmpresa());
        current.setCargo(exp.getCargo());
        current.setDescripcion(exp.getDescripcion());
        current.setAnios(exp.getAnios());
        ExperienciaLaboral updated = experienciaRepo.save(current);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long postulanteId, @PathVariable Long id) {
        ExperienciaLaboral exp = experienciaRepo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        if (!exp.getPostulante().getId().equals(postulanteId)) {
            return ResponseEntity.notFound().build();
        }
        experienciaRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
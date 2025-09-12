package cl.bne.curriculardata.api;

import cl.bne.curriculardata.api.service.ExperienciaService;
import cl.bne.curriculardata.dto.DTOExperienciaLaboral;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/personas/{postulanteId}/experiencias")
@RequiredArgsConstructor
public class ExperienciaLaboralController {

  private final ExperienciaService experienciaService;

  @GetMapping
  public List<DTOExperienciaLaboral> listar(@PathVariable("postulanteId") Long postulanteId) {
    return experienciaService.list(postulanteId);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DTOExperienciaLaboral> obtener(
      @PathVariable("postulanteId") Long postulanteId,
      @PathVariable("id") Long id) {
    return ResponseEntity.ok(experienciaService.get(postulanteId, id));
  }

  @PostMapping
  public ResponseEntity<DTOExperienciaLaboral> crear(
      @PathVariable("postulanteId") Long postulanteId,
      @Valid @RequestBody DTOExperienciaLaboral dto) {

    var creado = experienciaService.create(postulanteId, dto);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(creado.getId())
        .toUri();

    return ResponseEntity.created(location).body(creado);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DTOExperienciaLaboral> editar(
      @PathVariable("postulanteId") Long postulanteId,
      @PathVariable("id") Long id,
      @Valid @RequestBody DTOExperienciaLaboral dto) {

    var actualizado = experienciaService.update(postulanteId, id, dto);
    return ResponseEntity.ok(actualizado);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(
      @PathVariable("postulanteId") Long postulanteId,
      @PathVariable("id") Long id) {

    experienciaService.delete(postulanteId, id);
    return ResponseEntity.noContent().build();
  }
}
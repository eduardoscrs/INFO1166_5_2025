package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.repository.PostulanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personas")
@RequiredArgsConstructor
public class PostulanteController {

  private final PostulanteRepository repo;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Postulante crear(@RequestBody Postulante postulante) {
    postulante.setId(null); // que lo genere JPA
    return repo.save(postulante);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Postulante> obtener(@PathVariable Long id) {
    return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  public List<Postulante> listar() {
    return repo.findAll();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Postulante> editar(@PathVariable Long id, @RequestBody Postulante postulante) {
    return repo.findById(id)
      .map(p -> {
        p.setNombre(postulante.getNombre());
        p.setApellido(postulante.getApellido());
        p.setEmail(postulante.getEmail());
        return ResponseEntity.ok(repo.save(p));
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) {
    repo.deleteById(id);
  }
}

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
        p.setTipoDocumento(postulante.getTipoDocumento());
        p.setNumeroDocumento(postulante.getNumeroDocumento());
        p.setPrimerApellido(postulante.getPrimerApellido());
        p.setSegundoApellido(postulante.getSegundoApellido());
        p.setSexo(postulante.getSexo());
        p.setFechaNacimiento(postulante.getFechaNacimiento());
        p.setNacionalidad(postulante.getNacionalidad());
        p.setEstadoCivil(postulante.getEstadoCivil());
        p.setContacto(postulante.getContacto());
        p.setDireccion(postulante.getDireccion());
        return ResponseEntity.ok(repo.save(p));
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}/contacto")
  public ResponseEntity<Postulante> editarContacto(@PathVariable Long id, @RequestBody cl.bne.curriculardata.domain.DatosContacto contacto) {
    return repo.findById(id)
      .map(p -> {
        p.setContacto(contacto);
        return ResponseEntity.ok(repo.save(p));
      })
      .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}/direccion")
  public ResponseEntity<Postulante> editarDireccion(@PathVariable Long id, @RequestBody cl.bne.curriculardata.domain.Direccion direccion) {
    return repo.findById(id)
      .map(p -> {
        p.setDireccion(direccion);
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
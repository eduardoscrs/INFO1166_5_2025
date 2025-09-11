package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.repository.PostulanteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/personas")
public class PostulanteController {

    private final PostulanteRepository postulanteRepository;

    public PostulanteController(PostulanteRepository postulanteRepository) {
        this.postulanteRepository = postulanteRepository;
    }

    @GetMapping
    public List<Postulante> listar() {
        return postulanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Postulante obtener(@PathVariable Long id) {
        return postulanteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Postulante crear(@RequestBody Postulante postulante) {
        if (postulante.getExperiencias() == null) postulante.setExperiencias(List.of());
        return postulanteRepository.save(postulante);
    }

    @PutMapping("/{id}")
    public Postulante editar(@PathVariable Long id, @RequestBody Postulante postulante) {
        if (!postulanteRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado");
        postulante.setId(id);
        if (postulante.getExperiencias() == null) postulante.setExperiencias(List.of());
        return postulanteRepository.save(postulante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        if (!postulanteRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado");
        postulanteRepository.deleteById(id);
    }
}

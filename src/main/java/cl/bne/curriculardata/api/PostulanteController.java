package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.repository.PostulanteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {
    private final PostulanteRepository repository = new PostulanteRepository();

    @GetMapping
    public List<Postulante> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Postulante obtener(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Postulante crear(@RequestBody Postulante postulante) {
        return repository.save(postulante);
    }

    @PutMapping("/{id}")
    public Postulante editar(@PathVariable Long id, @RequestBody Postulante postulante) {
        postulante.setId(id);
        return repository.save(postulante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.delete(id);
    }
}
package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.repository.PostulanteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {
    private final PostulanteRepository repository = new PostulanteRepository();

    @GetMapping
    public List<Postulante> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Postulante obtener(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Postulante crear(@RequestBody Postulante postulante) {
        return repository.save(postulante);
    }

    @PutMapping("/{id}")
    public Postulante editar(@PathVariable Long id, @RequestBody Postulante postulante) {
        postulante.setId(id);
        return repository.save(postulante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.delete(id);
    }
}
package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {
    private final Map<Long, Postulante> postulantes = new HashMap<>();
    private long idCounter = 1;

    @GetMapping
    public List<Postulante> listar() {
        return new ArrayList<>(postulantes.values());
    }

    @GetMapping("/{id}")
    public Postulante obtener(@PathVariable Long id) {
        return postulantes.get(id);
    }

    @PostMapping
    public Postulante crear(@RequestBody Postulante postulante) {
        postulante.setId(idCounter++);
        postulantes.put(postulante.getId(), postulante);
        return postulante;
    }

    @PutMapping("/{id}")
    public Postulante editar(@PathVariable Long id, @RequestBody Postulante postulante) {
        postulante.setId(id);
        postulantes.put(id, postulante);
        return postulante;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        postulantes.remove(id);
    }
}

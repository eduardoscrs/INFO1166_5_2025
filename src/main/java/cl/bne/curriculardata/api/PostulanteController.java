package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/personas") // si prefieres, cambia a "/api/postulantes"
public class PostulanteController {

    private final Map<Long, Postulante> postulantes = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    @GetMapping
    public Collection<Postulante> listar() {
        return postulantes.values();
    }

    @GetMapping("/{id}")
    public Postulante obtener(@PathVariable Long id) {
        Postulante p = postulantes.get(id);
        if (p == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado");
        return p;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Postulante crear(@RequestBody Postulante postulante) {
        long id = idCounter.incrementAndGet();
        postulante.setId(id);
        if (postulante.getExperiencias() == null) postulante.setExperiencias(new ArrayList<>());
        postulantes.put(id, postulante);
        return postulante;
    }

    @PutMapping("/{id}")
    public Postulante editar(@PathVariable Long id, @RequestBody Postulante postulante) {
        if (!postulantes.containsKey(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado");
        postulante.setId(id);
        if (postulante.getExperiencias() == null) postulante.setExperiencias(new ArrayList<>());
        postulantes.put(id, postulante);
        return mapper.toDTO(postulante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        if (postulantes.remove(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado");
    }
}

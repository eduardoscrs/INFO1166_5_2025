package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/personas/{postulanteId}/experiencias")
public class ExperienciaLaboralController {

    // Mapa por postulante:  postulanteId -> (expId -> experiencia)
    private final Map<Long, Map<Long, ExperienciaLaboral>> store = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    private Map<Long, ExperienciaLaboral> bucket(Long postulanteId) {
        return store.computeIfAbsent(postulanteId, k -> new ConcurrentHashMap<>());
    }

    @GetMapping
    public Collection<ExperienciaLaboral> listar(@PathVariable Long postulanteId) {
        return bucket(postulanteId).values();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExperienciaLaboral crear(@PathVariable Long postulanteId,
                                    @RequestBody ExperienciaLaboral exp) {
        long id = idCounter.incrementAndGet();
        exp.setId(id);
        exp.setPostulanteId(postulanteId);
        bucket(postulanteId).put(id, exp);
        return exp;
    }

    @GetMapping("/{id}")
    public ExperienciaLaboral obtener(@PathVariable Long postulanteId, @PathVariable Long id) {
        var e = bucket(postulanteId).get(id);
        if (e == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada");
        return e;
    }

    @PutMapping("/{id}")
    public ExperienciaLaboral editar(@PathVariable Long postulanteId,
                                     @PathVariable Long id,
                                     @RequestBody ExperienciaLaboral exp) {
        var b = bucket(postulanteId);
        if (!b.containsKey(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada");
        exp.setId(id);
        exp.setPostulanteId(postulanteId);
        b.put(id, exp);
        return exp;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long postulanteId, @PathVariable Long id) {
        var b = bucket(postulanteId);
        if (b.remove(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada");
    }
}

package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaLaboralController {
    private final Map<Long, ExperienciaLaboral> experiencias = new HashMap<>();
    private long idCounter = 1;

    @GetMapping
    public List<ExperienciaLaboral> listar() {
        return new ArrayList<>(experiencias.values());
    }

    @PostMapping
    public ExperienciaLaboral crear(@RequestBody ExperienciaLaboral exp) {
        exp.setId(idCounter++);
        experiencias.put(exp.getId(), exp);
        return exp;
    }

    @PutMapping("/{id}")
    public ExperienciaLaboral editar(@PathVariable Long id, @RequestBody ExperienciaLaboral exp) {
        exp.setId(id);
        experiencias.put(id, exp);
        return exp;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        experiencias.remove(id);
    }
}

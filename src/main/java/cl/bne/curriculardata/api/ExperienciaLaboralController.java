package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.domain.DTOExperiencia.DTOExperienciaLaboral;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaLaboralController {

    private final Map<Long, ExperienciaLaboral> experiencias = new HashMap<>();
    private long idCounter = 1;

    // Mapper
    private DTOExperienciaLaboral toDto(ExperienciaLaboral e) {
        return new DTOExperienciaLaboral(e.getId(), e.getEmpresa(), e.getCargo(), e.getAnios());
    }

    private ExperienciaLaboral toEntity(DTOExperienciaLaboral d) {
        return new ExperienciaLaboral(d.getId(), d.getEmpresa(), d.getCargo(), null, d.getAnios());
    }

    // GET: listar todas
    @GetMapping
    public List<DTOExperienciaLaboral> listar() {
        return experiencias.values().stream().map(this::toDto).collect(Collectors.toList());
    }

    // POST: crear
    @PostMapping
    public DTOExperienciaLaboral crear(@RequestBody DTOExperienciaLaboral dto) {
        var entity = toEntity(dto);
        entity.setId(idCounter++);
        experiencias.put(entity.getId(), entity);
        return toDto(entity);
    }

    // PUT: editar
    @PutMapping("/{id}")
    public DTOExperienciaLaboral editar(@PathVariable Long id, @RequestBody DTOExperienciaLaboral dto) {
        var entity = toEntity(dto);
        entity.setId(id);
        experiencias.put(id, entity);
        return toDto(entity);
    }

    // DELETE: eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        experiencias.remove(id);
    }
}

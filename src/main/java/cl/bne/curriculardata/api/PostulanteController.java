package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.dto.PostulanteDTO;
import cl.bne.curriculardata.mapper.PostulanteMapper;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {
    private final Map<Long, Postulante> postulantes = new HashMap<>();
    private long idCounter = 1;
    private final PostulanteMapper mapper = PostulanteMapper.INSTANCE;

    @GetMapping
    public List<PostulanteDTO> listar() {
        return postulantes.values().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PostulanteDTO obtener(@PathVariable Long id) {
        Postulante postulante = postulantes.get(id);
        return postulante != null ? mapper.toDTO(postulante) : null;
    }

    @PostMapping
    public PostulanteDTO crear(@Valid @RequestBody PostulanteDTO dto) {
        Postulante postulante = mapper.toEntity(dto);
        postulante.setId(idCounter++);
        postulantes.put(postulante.getId(), postulante);
        return mapper.toDTO(postulante);
    }

    @PutMapping("/{id}")
    public PostulanteDTO editar(@PathVariable Long id, @Valid @RequestBody PostulanteDTO dto) {
        Postulante postulante = mapper.toEntity(dto);
        postulante.setId(id);
        postulantes.put(id, postulante);
        return mapper.toDTO(postulante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        postulantes.remove(id);
    }
}

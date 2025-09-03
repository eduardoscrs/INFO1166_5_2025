package cl.bne.curriculardata.repository;

import cl.bne.curriculardata.domain.Postulante;
import org.springframework.stereotype.Repository;  // ← Import agregado

import java.util.*;

@Repository  // ← Anotación AGREGADA
public class PostulanteRepository {
    private final Map<Long, Postulante> postulantes = new HashMap<>();
    private long idCounter = 1;

    public List<Postulante> findAll() {
        return new ArrayList<>(postulantes.values());
    }
    
    public Optional<Postulante> findById(Long id) {
        return Optional.ofNullable(postulantes.get(id));
    }
    
    public Postulante save(Postulante postulante) {
        if (postulante.getId() == null) {
            postulante.setId(idCounter++);
        }
        postulantes.put(postulante.getId(), postulante);
        return postulante;
    }
    
    public void deleteById(Long id) {
        postulantes.remove(id);
    }
}
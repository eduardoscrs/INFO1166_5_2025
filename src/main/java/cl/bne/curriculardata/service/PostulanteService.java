package cl.bne.curriculardata.service;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.repository.PostulanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulanteService {
    
    private final PostulanteRepository postulanteRepository;
    
    @Autowired  // ← Inyección de dependencia
    public PostulanteService(PostulanteRepository postulanteRepository) {
        this.postulanteRepository = postulanteRepository;
    }
    
    public List<Postulante> getAllPostulantes() {
        return postulanteRepository.findAll();
    }
    
    public Postulante getPostulanteById(Long id) {
        return postulanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Postulante no encontrado con ID: " + id));
    }
    
    public Postulante createPostulante(Postulante postulante) {
        return postulanteRepository.save(postulante);
    }
    
    public void deletePostulante(Long id) {
        postulanteRepository.deleteById(id);
    }
}
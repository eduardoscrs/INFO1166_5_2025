package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {
    
    private final PostulanteService postulanteService;
    
    @Autowired
    public PostulanteController(PostulanteService postulanteService) {
        this.postulanteService = postulanteService;
    }
    
    @GetMapping
    public List<Postulante> getAllPostulantes() {
        return postulanteService.getAllPostulantes();
    }
    
    @GetMapping("/{id}")
    public Postulante getPostulanteById(@PathVariable Long id) {
        return postulanteService.getPostulanteById(id);
    }
    
    @PostMapping
    public Postulante createPostulante(@RequestBody Postulante postulante) {
        return postulanteService.createPostulante(postulante);
    }
    
    @DeleteMapping("/{id}")
    public String deletePostulante(@PathVariable Long id) {
        postulanteService.deletePostulante(id);
        return "Postulante eliminado con éxito";
    }
}

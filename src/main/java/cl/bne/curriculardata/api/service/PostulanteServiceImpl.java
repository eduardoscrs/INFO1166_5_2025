package cl.bne.curriculardata.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import cl.bne.curriculardata.api.dto.*;
import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.repository.PostulanteRepository;

@Service @RequiredArgsConstructor
public class PostulanteServiceImpl implements PostulanteService {

  private final PostulanteRepository repo;

  @Override
  public Postulante create(PostulanteCreateDTO dto) {
    var p = new Postulante();
    p.setNombre(dto.getNombre());
    p.setApellido(dto.getApellido());
    p.setEmail(dto.getEmail());
    p.setExperiencias(new ArrayList<>());
    return repo.save(p);
  }

  @Override
  public Postulante update(Long id, PostulanteUpdateDTO dto) {
    var p = get(id);
    p.setNombre(dto.getNombre());
    p.setApellido(dto.getApellido());
    p.setEmail(dto.getEmail());
    return repo.save(p);
  }

  @Override public void delete(Long id) { repo.deleteById(id); }

  @Override public Postulante get(Long id) {
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Postulante no encontrado: " + id));
  }

  @Override public List<Postulante> list() { return repo.findAll(); }
}

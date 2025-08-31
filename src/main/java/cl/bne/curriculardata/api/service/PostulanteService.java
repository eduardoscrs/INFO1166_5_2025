package cl.bne.curriculardata.api.service;

import java.util.List;
import cl.bne.curriculardata.api.dto.*;
import cl.bne.curriculardata.domain.Postulante;

public interface PostulanteService {
  Postulante create(PostulanteCreateDTO dto);
  Postulante update(Long id, PostulanteUpdateDTO dto);
  void delete(Long id);
  Postulante get(Long id);
  List<Postulante> list();
}

package cl.bne.curriculardata.api.service;

import java.util.List;
import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.dto.PostulanteDTO;

public interface PostulanteService {
  Postulante create(PostulanteDTO dto);
  Postulante update(Long id, PostulanteDTO dto);
  void delete(Long id);
  Postulante get(Long id);
  List<Postulante> list();
}

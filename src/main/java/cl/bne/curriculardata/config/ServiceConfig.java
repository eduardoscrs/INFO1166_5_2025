package cl.bne.curriculardata.config;

import cl.bne.curriculardata.api.service.ExperienciaService;
import cl.bne.curriculardata.dto.DTOExperienciaLaboral;
import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.mapper.ExperienciaMapper;
import cl.bne.curriculardata.repository.ExperienciaLaboralRepository;
import cl.bne.curriculardata.repository.PostulanteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Configuration
public class ServiceConfig {

  @Bean
  public ExperienciaService experienciaService(
      ExperienciaLaboralRepository expRepo,
      PostulanteRepository posRepo,
      ExperienciaMapper mapper) {

    return new ExperienciaService() {
      @Override
      public List<DTOExperienciaLaboral> list(Long postulanteId) {
        return expRepo.findByPostulante_Id(postulanteId).stream()
            .map(mapper::toDto)
            .toList();
      }

      @Override
      public DTOExperienciaLaboral create(Long postulanteId, DTOExperienciaLaboral dto) {
        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));
        ExperienciaLaboral e = mapper.toEntity(dto);
        e.setPostulante(p);
        return mapper.toDto(expRepo.save(e));
      }

      @Override
      public DTOExperienciaLaboral get(Long postulanteId, Long expId) {
        ExperienciaLaboral e = expRepo.findById(expId)
            .filter(x -> x.getPostulante().getId().equals(postulanteId))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        return mapper.toDto(e);
      }

      @Override
      public DTOExperienciaLaboral update(Long postulanteId, Long expId, DTOExperienciaLaboral dto) {
        ExperienciaLaboral e = expRepo.findById(expId)
            .filter(x -> x.getPostulante().getId().equals(postulanteId))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        e.setEmpresa(dto.getEmpresa());
        e.setCargo(dto.getCargo());
        e.setAnios(dto.getAnios());
        return mapper.toDto(expRepo.save(e));
      }

      @Override
      public void delete(Long postulanteId, Long expId) {
        if (!expRepo.existsByIdAndPostulante_Id(expId, postulanteId)) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada");
        }
        expRepo.deleteByIdAndPostulante_Id(expId, postulanteId);
      }
    };
  }
}

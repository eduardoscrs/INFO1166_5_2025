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
        // Sin métodos custom: cargamos todo y filtramos por postulante
        return expRepo.findAll().stream()
            .filter(e -> e.getPostulante() != null
                      && postulanteId.equals(e.getPostulante().getId()))
            .map(mapper::toDto)  
            .toList();
      }

      @Override
      public DTOExperienciaLaboral create(Long postulanteId, DTOExperienciaLaboral dto) {
        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        ExperienciaLaboral e = mapper.toEntity(dto); 
        e.setPostulante(p);
        e = expRepo.save(e);
        return mapper.toDto(e);
      }

      @Override
      public DTOExperienciaLaboral get(Long postulanteId, Long expId) {
        ExperienciaLaboral e = expRepo.findById(expId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        return mapper.toDto(e);
      }

      @Override
      public DTOExperienciaLaboral update(Long postulanteId, Long expId, DTOExperienciaLaboral dto) {
        ExperienciaLaboral e = expRepo.findById(expId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));

        e.setEmpresa(dto.getEmpresa());
        e.setCargo(dto.getCargo());
        e.setAnios(dto.getAnios());
        e = expRepo.save(e);
        return mapper.toDto(e);
      }

      @Override
      public void delete(Long postulanteId, Long expId) {
        ExperienciaLaboral e = expRepo.findById(expId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        expRepo.delete(e);
      }
    };
  }
}

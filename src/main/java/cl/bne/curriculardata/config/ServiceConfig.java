package cl.bne.curriculardata.config;

import cl.bne.curriculardata.api.service.ExperienciaService;
import cl.bne.curriculardata.dto.DTOCondicionLaboralActual;
import cl.bne.curriculardata.dto.DTOExperienciaLaboral;
import cl.bne.curriculardata.dto.DTONivelEducacional;
import cl.bne.curriculardata.dto.DTOReferenciaLaboral;
import cl.bne.curriculardata.dto.DTOResumenPerfil;
import cl.bne.curriculardata.domain.ExperienciaLaboral;
import cl.bne.curriculardata.domain.Postulante;
import cl.bne.curriculardata.mapper.ExperienciaMapper;
import cl.bne.curriculardata.repository.ExperienciaLaboralRepository;
import cl.bne.curriculardata.repository.PostulanteRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

      @Override
      public DTOCondicionLaboralActual getCondicionLaboralActual(@NotNull @Positive Long postulanteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCondicionLaboralActual'");
      }

      @Override
      public DTOCondicionLaboralActual createCondicionLaboralActual(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOCondicionLaboralActual dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCondicionLaboralActual'");
      }

      @Override
      public DTOCondicionLaboralActual updateCondicionLaboralActual(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOCondicionLaboralActual dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCondicionLaboralActual'");
      }

      @Override
      public void deleteCondicionLaboralActual(@NotNull @Positive Long postulanteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCondicionLaboralActual'");
      }

      @Override
      public DTOResumenPerfil getResumenPerfil(@NotNull @Positive Long postulanteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResumenPerfil'");
      }

      @Override
      public DTOResumenPerfil createResumenPerfil(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOResumenPerfil dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createResumenPerfil'");
      }

      @Override
      public DTOResumenPerfil updateResumenPerfil(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOResumenPerfil dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateResumenPerfil'");
      }

      @Override
      public void deleteResumenPerfil(@NotNull @Positive Long postulanteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteResumenPerfil'");
      }

      @Override
      public List<DTOReferenciaLaboral> listReferencias(@NotNull @Positive Long postulanteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listReferencias'");
      }

      @Override
      public DTOReferenciaLaboral getReferencia(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long referenciaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReferencia'");
      }

      @Override
      public DTOReferenciaLaboral createReferencia(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOReferenciaLaboral dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReferencia'");
      }

      @Override
      public DTOReferenciaLaboral updateReferencia(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long referenciaId, @Valid @NotNull DTOReferenciaLaboral dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReferencia'");
      }

      @Override
      public void deleteReferencia(@NotNull @Positive Long postulanteId, @NotNull @Positive Long referenciaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReferencia'");
      }

      @Override
      public List<DTONivelEducacional> listNivelesEducacionales(@NotNull @Positive Long postulanteId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listNivelesEducacionales'");
      }

      @Override
      public DTONivelEducacional getNivelEducacional(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long nivelId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNivelEducacional'");
      }

      @Override
      public DTONivelEducacional createNivelEducacional(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTONivelEducacional dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNivelEducacional'");
      }

      @Override
      public DTONivelEducacional updateNivelEducacional(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long nivelId, @Valid @NotNull DTONivelEducacional dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateNivelEducacional'");
      }

      @Override
      public void deleteNivelEducacional(@NotNull @Positive Long postulanteId, @NotNull @Positive Long nivelId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteNivelEducacional'");
      }
    };
  }
}

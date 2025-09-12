package cl.bne.curriculardata.config;

import cl.bne.curriculardata.api.service.ExperienciaService;
import cl.bne.curriculardata.dto.*;
import cl.bne.curriculardata.domain.*;
import cl.bne.curriculardata.mapper.*;
import cl.bne.curriculardata.repository.*;
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
      ExperienciaMapper expMapper,

      // Repos y mappers adicionales
      CondicionLaboralActualRepository condRepo,
      ResumenPerfilRepository resumenRepo,
      ReferenciaLaboralRepository refRepo,
      NivelEducacionalRepository nivelRepo,

      CondicionLaboralActualMapper condMapper,
      ResumenPerfilMapper resumenMapper,
      ReferenciaLaboralMapper refMapper,
      NivelEducacionalMapper nivelMapper
  ) {

    return new ExperienciaService() {

      // =========================
      // EXPERIENCIA LABORAL (OK)
      // =========================
      @Override
      public List<DTOExperienciaLaboral> list(Long postulanteId) {
        return expRepo.findAll().stream()
            .filter(e -> e.getPostulante() != null
                      && postulanteId.equals(e.getPostulante().getId()))
            .map(expMapper::toDto)
            .toList();
      }

      @Override
      public DTOExperienciaLaboral create(Long postulanteId, DTOExperienciaLaboral dto) {
        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        ExperienciaLaboral e = expMapper.toEntity(dto);
        e.setPostulante(p);
        e = expRepo.save(e);
        return expMapper.toDto(e);
      }

      @Override
      public DTOExperienciaLaboral get(Long postulanteId, Long expId) {
        ExperienciaLaboral e = expRepo.findById(expId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        return expMapper.toDto(e);
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
        return expMapper.toDto(e);
      }

      @Override
      public void delete(Long postulanteId, Long expId) {
        ExperienciaLaboral e = expRepo.findById(expId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Experiencia no encontrada"));
        expRepo.delete(e);
      }

      // ==========================================
      // CONDICIÓN LABORAL ACTUAL (uno por postulante)
      // ==========================================
      @Override
      public DTOCondicionLaboralActual getCondicionLaboralActual(@NotNull @Positive Long postulanteId) {
        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        CondicionLaboralActual c = condRepo.findAll().stream()
            .filter(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Condición laboral no encontrada"));

        return condMapper.toDto(c);
      }

      @Override
      public DTOCondicionLaboralActual createCondicionLaboralActual(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOCondicionLaboralActual dto) {

        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        // Si ya existe, puedes optar por 409 o actualizar. Aquí lanzamos 409.
        boolean exists = condRepo.findAll().stream()
            .anyMatch(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()));
        if (exists) {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una condición laboral para este postulante");
        }

        CondicionLaboralActual c = condMapper.toEntity(dto);
        c.setPostulante(p);
        c = condRepo.save(c);
        return condMapper.toDto(c);
      }

      @Override
      public DTOCondicionLaboralActual updateCondicionLaboralActual(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOCondicionLaboralActual dto) {

        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        CondicionLaboralActual c = condRepo.findAll().stream()
            .filter(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Condición laboral no encontrada"));

        c.setSituacionLaboral(dto.getSituacionLaboral());
        c.setFechaInicioSituacion(dto.getFechaInicioSituacion());
        c.setUltimoSalarioLiquidoMensual(dto.getUltimoSalarioLiquidoMensual());
        c.setUltimaActividadLaboral(dto.getUltimaActividadLaboral());
        c.setBuscandoEmpleo(dto.getBuscandoEmpleo());
        c = condRepo.save(c);
        return condMapper.toDto(c);
      }

      @Override
      public void deleteCondicionLaboralActual(@NotNull @Positive Long postulanteId) {
        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        CondicionLaboralActual c = condRepo.findAll().stream()
            .filter(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Condición laboral no encontrada"));

        condRepo.delete(c);
      }

      // ==============================
      // RESUMEN PERFIL (uno por postulante)
      // ==============================
      @Override
      public DTOResumenPerfil getResumenPerfil(@NotNull @Positive Long postulanteId) {
        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        ResumenPerfil r = resumenRepo.findAll().stream()
            .filter(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resumen de perfil no encontrado"));

        return resumenMapper.toDto(r);
      }

      @Override
      public DTOResumenPerfil createResumenPerfil(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOResumenPerfil dto) {

        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        boolean exists = resumenRepo.findAll().stream()
            .anyMatch(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()));
        if (exists) {
          throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un resumen de perfil para este postulante");
        }

        ResumenPerfil r = resumenMapper.toEntity(dto);
        r.setPostulante(p);
        r = resumenRepo.save(r);
        return resumenMapper.toDto(r);
      }

      @Override
      public DTOResumenPerfil updateResumenPerfil(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOResumenPerfil dto) {

        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        ResumenPerfil r = resumenRepo.findAll().stream()
            .filter(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resumen de perfil no encontrado"));

        r.setDescripcion(dto.getDescripcion());
        r = resumenRepo.save(r);
        return resumenMapper.toDto(r);
      }

      @Override
      public void deleteResumenPerfil(@NotNull @Positive Long postulanteId) {
        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        ResumenPerfil r = resumenRepo.findAll().stream()
            .filter(x -> x.getPostulante() != null && x.getPostulante().getId().equals(p.getId()))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resumen de perfil no encontrado"));

        resumenRepo.delete(r);
      }

      // ==============================
      // REFERENCIAS LABORALES (lista)
      // ==============================
      @Override
      public List<DTOReferenciaLaboral> listReferencias(@NotNull @Positive Long postulanteId) {
        return refRepo.findAll().stream()
            .filter(r -> r.getPostulante() != null
                      && postulanteId.equals(r.getPostulante().getId()))
            .map(refMapper::toDto)
            .toList();
      }

      @Override
      public DTOReferenciaLaboral getReferencia(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long referenciaId) {

        ReferenciaLaboral r = refRepo.findById(referenciaId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Referencia no encontrada"));

        return refMapper.toDto(r);
      }

      @Override
      public DTOReferenciaLaboral createReferencia(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTOReferenciaLaboral dto) {

        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        ReferenciaLaboral r = refMapper.toEntity(dto);
        r.setPostulante(p);
        r = refRepo.save(r);
        return refMapper.toDto(r);
      }

      @Override
      public DTOReferenciaLaboral updateReferencia(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long referenciaId, @Valid @NotNull DTOReferenciaLaboral dto) {

        ReferenciaLaboral r = refRepo.findById(referenciaId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Referencia no encontrada"));

        r.setNombreCompleto(dto.getNombreCompleto());
        r.setPuesto(dto.getPuesto());
        r.setEmpresa(dto.getEmpresa());
        r.setNroTelefonico(dto.getNroTelefonico());
        r.setEmail(dto.getEmail());
        r = refRepo.save(r);
        return refMapper.toDto(r);
      }

      @Override
      public void deleteReferencia(@NotNull @Positive Long postulanteId, @NotNull @Positive Long referenciaId) {

        ReferenciaLaboral r = refRepo.findById(referenciaId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Referencia no encontrada"));

        refRepo.delete(r);
      }

      // ==============================
      // NIVELES EDUCACIONALES (lista)
      // ==============================
      @Override
      public List<DTONivelEducacional> listNivelesEducacionales(@NotNull @Positive Long postulanteId) {
        return nivelRepo.findAll().stream()
            .filter(n -> n.getPostulante() != null
                      && postulanteId.equals(n.getPostulante().getId()))
            .map(nivelMapper::toDto)
            .toList();
      }

      @Override
      public DTONivelEducacional getNivelEducacional(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long nivelId) {

        NivelEducacional n = nivelRepo.findById(nivelId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nivel educacional no encontrado"));

        return nivelMapper.toDto(n);
      }

      @Override
      public DTONivelEducacional createNivelEducacional(@NotNull @Positive Long postulanteId,
          @Valid @NotNull DTONivelEducacional dto) {

        Postulante p = posRepo.findById(postulanteId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postulante no encontrado"));

        NivelEducacional n = nivelMapper.toEntity(dto);
        n.setPostulante(p);
        n = nivelRepo.save(n);
        return nivelMapper.toDto(n);
      }

      @Override
      public DTONivelEducacional updateNivelEducacional(@NotNull @Positive Long postulanteId,
          @NotNull @Positive Long nivelId, @Valid @NotNull DTONivelEducacional dto) {

        NivelEducacional n = nivelRepo.findById(nivelId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nivel educacional no encontrado"));

        n.setNivel(dto.getNivel());
        n.setArea(dto.getArea());
        n.setInstitucion(dto.getInstitucion());
        n.setTitulo(dto.getTitulo());
        n.setAnioEgreso(dto.getAnioEgreso());
        n = nivelRepo.save(n);
        return nivelMapper.toDto(n);
      }

      @Override
      public void deleteNivelEducacional(@NotNull @Positive Long postulanteId, @NotNull @Positive Long nivelId) {

        NivelEducacional n = nivelRepo.findById(nivelId)
            .filter(x -> x.getPostulante() != null
                      && postulanteId.equals(x.getPostulante().getId()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nivel educacional no encontrado"));

        nivelRepo.delete(n);
      }
    };
  }
}

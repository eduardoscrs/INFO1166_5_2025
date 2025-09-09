package cl.bne.curriculardata.api.service;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import cl.bne.curriculardata.dto.DTOExperienciaLaboral;

@Validated
public interface ExperienciaService {

    List<DTOExperienciaLaboral> list(
            @NotNull @Positive Long postulanteId);

    DTOExperienciaLaboral create(
            @NotNull @Positive Long postulanteId,
            @Valid @NotNull DTOExperienciaLaboral dto);

    DTOExperienciaLaboral get(
            @NotNull @Positive Long postulanteId,
            @NotNull @Positive Long expId);

    DTOExperienciaLaboral update(
            @NotNull @Positive Long postulanteId,
            @NotNull @Positive Long expId,
            @Valid @NotNull DTOExperienciaLaboral dto);

    void delete(
            @NotNull @Positive Long postulanteId,
            @NotNull @Positive Long expId);
}

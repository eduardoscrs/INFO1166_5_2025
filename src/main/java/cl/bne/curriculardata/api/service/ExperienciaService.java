package cl.bne.curriculardata.api.service;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import cl.bne.curriculardata.dto.DTOExperienciaLaboral;
import cl.bne.curriculardata.dto.DTOCondicionLaboralActual;
import cl.bne.curriculardata.dto.DTOResumenPerfil;
import cl.bne.curriculardata.dto.DTOReferenciaLaboral;
import cl.bne.curriculardata.dto.DTONivelEducacional;

@Validated
public interface ExperienciaService {

    // --- EXPERIENCIA LABORAL ---
    List<DTOExperienciaLaboral> list(@NotNull @Positive Long postulanteId);

    DTOExperienciaLaboral create(@NotNull @Positive Long postulanteId,
                                 @Valid @NotNull DTOExperienciaLaboral dto);

    DTOExperienciaLaboral get(@NotNull @Positive Long postulanteId,
                              @NotNull @Positive Long expId);

    DTOExperienciaLaboral update(@NotNull @Positive Long postulanteId,
                                 @NotNull @Positive Long expId,
                                 @Valid @NotNull DTOExperienciaLaboral dto);

    void delete(@NotNull @Positive Long postulanteId,
                @NotNull @Positive Long expId);

    // --- CONDICIÓN LABORAL ACTUAL ---
    DTOCondicionLaboralActual getCondicionLaboralActual(@NotNull @Positive Long postulanteId);

    DTOCondicionLaboralActual createCondicionLaboralActual(@NotNull @Positive Long postulanteId,
                                                           @Valid @NotNull DTOCondicionLaboralActual dto);

    DTOCondicionLaboralActual updateCondicionLaboralActual(@NotNull @Positive Long postulanteId,
                                                           @Valid @NotNull DTOCondicionLaboralActual dto);

    void deleteCondicionLaboralActual(@NotNull @Positive Long postulanteId);

    // --- RESUMEN DE PERFIL ---
    DTOResumenPerfil getResumenPerfil(@NotNull @Positive Long postulanteId);

    DTOResumenPerfil createResumenPerfil(@NotNull @Positive Long postulanteId,
                                         @Valid @NotNull DTOResumenPerfil dto);

    DTOResumenPerfil updateResumenPerfil(@NotNull @Positive Long postulanteId,
                                         @Valid @NotNull DTOResumenPerfil dto);

    void deleteResumenPerfil(@NotNull @Positive Long postulanteId);

    // --- REFERENCIAS LABORALES ---
    List<DTOReferenciaLaboral> listReferencias(@NotNull @Positive Long postulanteId);

    DTOReferenciaLaboral getReferencia(@NotNull @Positive Long postulanteId,
                                       @NotNull @Positive Long referenciaId);

    DTOReferenciaLaboral createReferencia(@NotNull @Positive Long postulanteId,
                                          @Valid @NotNull DTOReferenciaLaboral dto);

    DTOReferenciaLaboral updateReferencia(@NotNull @Positive Long postulanteId,
                                          @NotNull @Positive Long referenciaId,
                                          @Valid @NotNull DTOReferenciaLaboral dto);

    void deleteReferencia(@NotNull @Positive Long postulanteId,
                          @NotNull @Positive Long referenciaId);

    // --- NIVELES EDUCACIONALES ---
    List<DTONivelEducacional> listNivelesEducacionales(@NotNull @Positive Long postulanteId);

    DTONivelEducacional getNivelEducacional(@NotNull @Positive Long postulanteId,
                                            @NotNull @Positive Long nivelId);

    DTONivelEducacional createNivelEducacional(@NotNull @Positive Long postulanteId,
                                               @Valid @NotNull DTONivelEducacional dto);

    DTONivelEducacional updateNivelEducacional(@NotNull @Positive Long postulanteId,
                                               @NotNull @Positive Long nivelId,
                                               @Valid @NotNull DTONivelEducacional dto);

    void deleteNivelEducacional(@NotNull @Positive Long postulanteId,
                                @NotNull @Positive Long nivelId);
}

package cl.bne.curriculardata.api;


import cl.bne.curriculardata.api.service.ExperienciaService;
import cl.bne.curriculardata.dto.DTOExperienciaLaboral;
import cl.bne.curriculardata.dto.DTOCondicionLaboralActual;
import cl.bne.curriculardata.dto.DTOResumenPerfil;
import cl.bne.curriculardata.dto.DTOReferenciaLaboral;
import cl.bne.curriculardata.dto.DTONivelEducacional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/personas/{postulanteId}")
@RequiredArgsConstructor
public class ExperienciaLaboralController {
    private final ExperienciaService experienciaService;


    // --- EXPERIENCIA LABORAL ---
    @GetMapping("/experiencias")
    public List<DTOExperienciaLaboral> listarExperiencias(@PathVariable Long postulanteId) {
        return experienciaService.list(postulanteId);
    }

    @GetMapping("/experiencias/{id}")
    public ResponseEntity<DTOExperienciaLaboral> obtenerExperiencia(
            @PathVariable Long postulanteId,
            @PathVariable Long id) {
        return ResponseEntity.ok(experienciaService.get(postulanteId, id));
    }

    @PostMapping("/experiencias")
    public ResponseEntity<DTOExperienciaLaboral> crearExperiencia(
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTOExperienciaLaboral dto) {
        var creado = experienciaService.create(postulanteId, dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/experiencias/{id}")
    public ResponseEntity<DTOExperienciaLaboral> editarExperiencia(
            @PathVariable Long postulanteId,
            @PathVariable Long id,
            @Valid @RequestBody DTOExperienciaLaboral dto) {
        var actualizado = experienciaService.update(postulanteId, id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/experiencias/{id}")
    public ResponseEntity<Void> eliminarExperiencia(
            @PathVariable Long postulanteId,
            @PathVariable Long id) {
        experienciaService.delete(postulanteId, id);
        return ResponseEntity.noContent().build();
    }


    // --- CONDICIÓN LABORAL ACTUAL ---
    @GetMapping("/condicion-laboral-actual")
    public ResponseEntity<DTOCondicionLaboralActual> obtenerCondicionLaboralActual(@PathVariable Long postulanteId) {
        return ResponseEntity.ok(experienciaService.getCondicionLaboralActual(postulanteId));
    }

    @PostMapping("/condicion-laboral-actual")
    public ResponseEntity<DTOCondicionLaboralActual> crearCondicionLaboralActual(
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTOCondicionLaboralActual dto) {
        return ResponseEntity.ok(experienciaService.createCondicionLaboralActual(postulanteId, dto));
    }

    @PutMapping("/condicion-laboral-actual")
    public ResponseEntity<DTOCondicionLaboralActual> actualizarCondicionLaboralActual(
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTOCondicionLaboralActual dto) {
        return ResponseEntity.ok(experienciaService.updateCondicionLaboralActual(postulanteId, dto));
    }

    @DeleteMapping("/condicion-laboral-actual")
    public ResponseEntity<Void> eliminarCondicionLaboralActual(@PathVariable Long postulanteId) {
        experienciaService.deleteCondicionLaboralActual(postulanteId);
        return ResponseEntity.noContent().build();
    }


    // --- RESUMEN DE PERFIL ---
    @GetMapping("/resumen-perfil")
    public ResponseEntity<DTOResumenPerfil> obtenerResumenPerfil(@PathVariable Long postulanteId) {
        return ResponseEntity.ok(experienciaService.getResumenPerfil(postulanteId));
    }

    @PostMapping("/resumen-perfil")
    public ResponseEntity<DTOResumenPerfil> crearResumenPerfil(
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTOResumenPerfil dto) {
        return ResponseEntity.ok(experienciaService.createResumenPerfil(postulanteId, dto));
    }

    @PutMapping("/resumen-perfil")
    public ResponseEntity<DTOResumenPerfil> actualizarResumenPerfil(
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTOResumenPerfil dto) {
        return ResponseEntity.ok(experienciaService.updateResumenPerfil(postulanteId, dto));
    }

    @DeleteMapping("/resumen-perfil")
    public ResponseEntity<Void> eliminarResumenPerfil(@PathVariable Long postulanteId) {
        experienciaService.deleteResumenPerfil(postulanteId);
        return ResponseEntity.noContent().build();
    }


    // --- REFERENCIAS LABORALES ---
    @GetMapping("/referencias")
    public List<DTOReferenciaLaboral> listarReferencias(@PathVariable Long postulanteId) {
        return experienciaService.listReferencias(postulanteId);
    }

    @GetMapping("/referencias/{id}")
    public ResponseEntity<DTOReferenciaLaboral> obtenerReferencia(
            @PathVariable Long postulanteId,
            @PathVariable Long id) {
        return ResponseEntity.ok(experienciaService.getReferencia(postulanteId, id));
    }

    @PostMapping("/referencias")
    public ResponseEntity<DTOReferenciaLaboral> crearReferencia(
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTOReferenciaLaboral dto) {
        var creado = experienciaService.createReferencia(postulanteId, dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/referencias/{id}")
    public ResponseEntity<DTOReferenciaLaboral> editarReferencia(
            @PathVariable Long postulanteId,
            @PathVariable Long id,
            @Valid @RequestBody DTOReferenciaLaboral dto) {
        var actualizado = experienciaService.updateReferencia(postulanteId, id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/referencias/{id}")
    public ResponseEntity<Void> eliminarReferencia(
            @PathVariable Long postulanteId,
            @PathVariable Long id) {
        experienciaService.deleteReferencia(postulanteId, id);
        return ResponseEntity.noContent().build();
    }


    // --- NIVELES EDUCACIONALES ---
    @GetMapping("/niveles-educacionales")
    public List<DTONivelEducacional> listarNivelesEducacionales(@PathVariable Long postulanteId) {
        return experienciaService.listNivelesEducacionales(postulanteId);
    }

    @GetMapping("/niveles-educacionales/{id}")
    public ResponseEntity<DTONivelEducacional> obtenerNivelEducacional(
            @PathVariable Long postulanteId,
            @PathVariable Long id) {
        return ResponseEntity.ok(experienciaService.getNivelEducacional(postulanteId, id));
    }

    @PostMapping("/niveles-educacionales")
    public ResponseEntity<DTONivelEducacional> crearNivelEducacional(
            @PathVariable Long postulanteId,
            @Valid @RequestBody DTONivelEducacional dto) {
        var creado = experienciaService.createNivelEducacional(postulanteId, dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/niveles-educacionales/{id}")
    public ResponseEntity<DTONivelEducacional> editarNivelEducacional(
            @PathVariable Long postulanteId,
            @PathVariable Long id,
            @Valid @RequestBody DTONivelEducacional dto) {
        var actualizado = experienciaService.updateNivelEducacional(postulanteId, id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/niveles-educacionales/{id}")
    public ResponseEntity<Void> eliminarNivelEducacional(
            @PathVariable Long postulanteId,
            @PathVariable Long id) {
        experienciaService.deleteNivelEducacional(postulanteId, id);
        return ResponseEntity.noContent().build();
    }
}

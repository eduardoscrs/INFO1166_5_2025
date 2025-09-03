package cl.bne.curriculardata.api;

import cl.bne.curriculardata.domain.ExperienciaLaboral;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias")
@Tag(name = "Experiencias Laborales", description = "Operaciones para gestión de experiencias laborales")
public class ExperienciaLaboralController {

    @Operation(summary = "Obtener todas las experiencias laborales", 
               description = "Retorna una lista de todas las experiencias laborales registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de experiencias obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron experiencias laborales")
    })
    @GetMapping
    public List<ExperienciaLaboral> getAllExperiencias() {
        // Tu lógica existente aquí
        return List.of(); // Esto es solo un placeholder
    }

    @Operation(summary = "Obtener experiencia laboral por ID", 
               description = "Retorna una experiencia laboral específica basada en su ID único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Experiencia laboral encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Experiencia laboral no encontrada")
    })
    @GetMapping("/{id}")
    public ExperienciaLaboral getExperienciaById(
            @Parameter(description = "ID único de la experiencia laboral", example = "1", required = true)
            @PathVariable Long id) {
        // Tu lógica existente aquí
        return new ExperienciaLaboral(); // Esto es solo un placeholder
    }

    @Operation(summary = "Crear nueva experiencia laboral", 
               description = "Registra una nueva experiencia laboral en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Experiencia laboral creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ExperienciaLaboral createExperiencia(
            @Parameter(description = "Datos de la experiencia laboral a crear", required = true) 
            @RequestBody ExperienciaLaboral experiencia) {
        // Tu lógica existente aquí
        return experiencia; // Esto es solo un placeholder
    }

    @Operation(summary = "Actualizar experiencia laboral existente", 
               description = "Actualiza los datos de una experiencia laboral existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Experiencia laboral actualizada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Experiencia laboral no encontrada")
    })
    @PutMapping("/{id}")
    public ExperienciaLaboral updateExperiencia(
            @Parameter(description = "ID de la experiencia laboral a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @Parameter(description = "Datos actualizados de la experiencia laboral", required = true)
            @RequestBody ExperienciaLaboral experiencia) {
        // Tu lógica existente aquí
        return experiencia; // Esto es solo un placeholder
    }

    @Operation(summary = "Eliminar experiencia laboral", 
               description = "Elimina una experiencia laboral del sistema basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Experiencia laboral eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Experiencia laboral no encontrada")
    })
    @DeleteMapping("/{id}")
    public void deleteExperiencia(
            @Parameter(description = "ID de la experiencia laboral a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        // Tu lógica existente aquí
    }
}
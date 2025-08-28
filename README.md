# Organización de tareas para el equipo

Este microservicio permite gestionar datos personales y experiencia laboral de postulantes. El desarrollo se realiza en Java con Spring Boot, almacenamiento en memoria (sin base de datos real) y pruebas locales.

## Tareas y responsables

### 1. Entidad y DTO Postulante (Persona 1)
- Crear la clase `Postulante` con atributos: id, nombre, apellido, email, lista de experiencias.
- Crear un DTO para exponer solo los datos necesarios (puede ser igual a la entidad si no hay datos sensibles).
- Agregar validaciones básicas (por ejemplo, email no vacío, nombre requerido).

### 2. Entidad y DTO ExperienciaLaboral (Persona 2)
- Crear la clase `ExperienciaLaboral` con atributos: id, empresa, cargo, años, descripción.
- Crear un DTO para exponer solo los datos necesarios.
- Agregar validaciones básicas (por ejemplo, empresa y cargo requeridos).

### 3. Repositorio en memoria y lógica de negocio (Persona 3)
- Implementar almacenamiento en memoria usando HashMap para postulantes y experiencias.
- Métodos CRUD: crear, leer, actualizar, eliminar.
- Métodos para asociar experiencias a postulantes.

### 4. Controlador REST de Postulante (Persona 4)
- Crear endpoints para crear, editar, listar, obtener y eliminar postulantes.
- Endpoint para asociar experiencias laborales a un postulante.
- Usar los DTOs definidos.

### 5. Controlador REST de ExperienciaLaboral (Persona 5)
- Crear endpoints para agregar, editar, listar y eliminar experiencias laborales.
- Permitir asociar/desasociar experiencias a un postulante.
- Usar los DTOs definidos.

### 6. Documentación, pruebas y validaciones (Persona 6)
- Configurar Swagger/OpenAPI para documentar los endpoints.
- Probar los endpoints con Postman o curl.
- Documentar en este README cómo probar cada endpoint y ejemplos de uso.
- Revisar que las validaciones funcionen y que los errores sean claros.

## Notas
- Todo el almacenamiento es en memoria, por lo que los datos se pierden al reiniciar la app.
- El microservicio es completamente funcional para pruebas y desarrollo local.
- Cuando se requiera persistencia real, solo se debe cambiar la implementación del repositorio.

# Documentación y pruebas

- Los endpoints están documentados con Swagger.
- Prueba los endpoints con Postman o curl.
- Ejemplo para crear un postulante:

```bash
curl -X POST http://localhost:8080/api/postulantes -H "Content-Type: application/json" -d '{"nombre":"Juan","apellido":"Pérez","email":"juan@correo.com","experiencias":[]}'
```

- Ejemplo para crear una experiencia laboral:

```bash
curl -X POST http://localhost:8080/api/experiencias -H "Content-Type: application/json" -d '{"empresa":"Empresa X","cargo":"Desarrollador","anios":2,"descripcion":"Backend"}'
```

- Agrega validaciones y mejora los DTOs según lo requiera la interfaz.

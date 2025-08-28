# Documentación y pruebas

- Los endpoints están documentados con Swagger/OpenAPI (si tienes springdoc-openapi en el pom).
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

# Servicio de Datos Curriculares (Spring Boot)

Este microservicio permite gestionar, editar y visualizar los datos personales y experiencia laboral de postulantes.

## Tecnologías principales
- Java 21 (OpenJDK)
- Spring Boot 3.5.x
- Maven 3.9.x
- Lombok
- MapStruct

## ¿Cómo ejecutar el proyecto?

1. **Clona el repositorio y entra a la carpeta del proyecto:**
   ```
   git clone <url-del-repo>
   cd INFO1166_5_2025
   ```

2. **Compila y descarga dependencias:**
   ```
   mvn clean install
   ```

3. **Ejecuta el microservicio:**
   ```
   mvn spring-boot:run
   ```
   El servicio quedará disponible en `http://localhost:8080`.

4. **Prueba los endpoints:**
   Puedes usar Postman, Insomnia o `curl`.
   - Listar postulantes:
     ```
     curl http://localhost:8080/api/postulantes
     ```
   - Crear postulante:
     ```
     curl -X POST http://localhost:8080/api/postulantes -H "Content-Type: application/json" -d '{"nombre":"Juan","apellido":"Pérez","email":"juan@correo.com","experiencias":[]}'
     ```

## Notas
- Los datos se almacenan en memoria (se pierden al reiniciar).
- Si tu IDE marca errores de Lombok, instala la extensión correspondiente y recarga el proyecto.
- Puedes modificar y extender la lógica según tus necesidades.

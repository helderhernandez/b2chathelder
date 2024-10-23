# Prueba técnica práctica para un desarrollador de software backend

# Instrucciones para ejecutar
### Bases de datos
Se proporcionan 2 bases de datos (ambas podra encontrarlas en el directorio `/b2chathelder\database-util`):
- `db prod - b2chat_helder` (para producción)
- `db prod - b2chat_helder_test` (para pruebas)

Al instalar las DB se configuraran automaticamente las tablas, usuario y permisos necesarios para poder ejecutar la API.

### API Rest
Luego de instalar las DBs puede ejecutar la API rest, y acceder a la documentación `http://localhost:8080/swagger-ui/index.html`

# Stack de lenguajes y herramientas
- Java JDK 21
- Eclipse IDE (con STS y Lombok)
- MySQL y MySQL Workbench

# Explicación breve sobre el desarrollo
- Se utilizo la arquitectura hexagonal
- Para el flujo de crear usuario solo se consideran los campos `username`, `password` y `email`
- Para el flujo de editar usuario solo se consideran los campos `username`, `password` y `isActive`
- Se agregaron los campos extra: `isActive` y `isDelete`
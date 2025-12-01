# API REST - Spring Boot: Vehículos, Reparaciones y Piezas

Proyecto REST en Spring Boot que gestiona vehículos, reparaciones y piezas.

## 🔎 Descripción

Este proyecto es un ejemplo de API REST construida con Spring Boot (Java) que ofrece operaciones CRUD para:
- Vehículos (Vehiculo)
- Reparaciones (Reparacion)
- Piezas (Pieza)

La comunicación con la base de datos se realiza mediante Spring Data JPA; por defecto se configura una base de datos MySQL/MariaDB.

## 🛠 Tecnologías y dependencias

- Java 21 (configurado en `pom.xml`)
- Spring Boot 3.5.8
- Spring Data JPA
- MySQL / MariaDB (conector: `mysql-connector-j`)
- Lombok
- Docker Compose (opcional para levantar MariaDB + phpMyAdmin)
- Maven (usando wrapper: `mvnw`/`mvnw.cmd`)

## 📁 Estructura principal

- `src/main/java/com/example/exam/` — Código principal
  - `controller/` — Controladores REST (VehiculoController, ReparacionController, PiezaController)
  - `service/` — Lógica de negocio
  - `repository/` — Interfaces JPA
  - `models/` — Entidades JPA
  - `dto/` — Registros DTO para create/modify
  - `error/` — Exceptions personalizadas
- `src/main/resources/` — Archivos configuración
  - `application.properties` — Configuración (DB, puerto, etc.)
- `docker-compose.yml` — Opcional: levanta MariaDB + phpMyAdmin

## ⚙️ Configuración

La conexión por defecto con la base de datos está en `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/examenRafaelGarcia
spring.datasource.username=root
spring.datasource.password=password123
server.port=8070
```

Si quieres ejecutar con Docker (recomendado para desarrollo local), ejecuta:

```powershell
# Desde PowerShell (Windows)
docker-compose up -d
```

Esto levantará MariaDB en el puerto 3306 y phpMyAdmin en el puerto 8090.

## ▶️ Ejecutar localmente

Recomendado (Windows PowerShell):

```powershell
# Construir el proyecto
automate: .\mvnw.cmd clean package

# Iniciar aplicación
.\mvnw.cmd spring-boot:run
```

Alternativamente, puedes ejecutar el JAR generado:

```powershell
java -jar target/exam-0.0.1-SNAPSHOT.jar
```

La API escuchará por defecto en el puerto 8070: `http://localhost:8070`.

## 📮 Endpoints principales

Base: `http://localhost:8070/api/private/`

### Vehículos (VehiculoController)

- GET `/vehiculos/` — Obtener todos los vehículos
- GET `/vehiculos/{id}` — Obtener vehículo por ID
- GET `/vehiculos/get?marca={marca}` — Obtener vehículos por marca
- GET `/vehiculos/get?modelo={modelo}` — Obtener vehículos por modelo
- POST `/vehiculos/add` — Añadir un vehículo
  - Body JSON: `{ "marca": "Toyota", "modelo": "Corolla" }`
- POST `/vehiculos/modify{id}` — Modificar un vehículo (id en la ruta, `VehiculoDto` en body)
- DELETE `/vehiculos/delete{id}` — Eliminar vehículo por id

---

### Reparaciones (ReparacionController)

- GET `/reparacion/` — Listar reparaciones
- GET `/reparacion/{id}` — Obtener reparación por id
- GET `/reparacion/get{fecha}` — Obtener reparaciones por fecha (fecha en PathVariable)
- POST `/reparacion/add` — Añadir reparación
  - Body JSON: `{ "fecha": "2023-12-01", "coste": 200, "id_vehiculo": 1 }`
- POST `/reparacion/modify{id}` — Modificar reparación (id en ruta, DTO en body)
- DELETE `/reparacion/delete{id}` — Eliminar reparación por id

---

### Piezas (PiezaController)

- GET `/piezas/` — Listar piezas
- GET `/piezas/get{id}` — Obtener pieza por id
- GET `/piezas/getNombre{nombre}` — Buscar piezas por nombre
- POST `/piezas/add` — Añadir pieza
  - Body JSON: `{ "nombre": "Filtro", "coste": 20 }`
- POST `/piezas/modify{id}` — Modificar pieza
- DELETE `/piezas/delete{id}` — Eliminar pieza por id

> ⚠️ Nota: Algunos endpoints usan ruta con sintaxis `get{id}` o `delete{id}` (sin `/` antes de `{id}`) tal como están implementados en el código. Esto puede ser inconsistente con el uso habitual de `/{id}` — revisa los controladores si se desea estandarizar las rutas.

## 🧩 Modelo de datos (resumen)

- Vehiculo
  - `id`, `marca`, `modelo`
  - Un Vehiculo tiene una lista de Reparacion
- Reparacion
  - `id`, `fecha`, `coste`, relación a un `Vehiculo`, y lista de `Pieza` (ManyToMany)
- Pieza
  - `id`, `nombre`, `coste`, lista de Reparacion (ManyToMany)

## 🧪 Tests

Solo existe una prueba de arranque del contexto (Spring Boot test). Ejecuta:

```powershell
.\mvnw.cmd test
```

## 🚨 Notas y recomendaciones

- En `pom.xml` se requiere Java 21 (`<java.version>21</java.version>`).
- La configuración de seguridad (`SecurityConfig`) está presente en `src/main/java/com/example/config` pero está comentada: por ahora no se aplican reglas de seguridad en la aplicación.
- Hay algunas inconsistencias (por ejemplo, `ReparacionService.add` retorna una entidad sin guardarla en el repositorio) que conviene revisar si deseas comportamiento persistente inmediato.
- Algunas rutas usan `get{id}` o `delete{id}` (sin slash), que pueden dificultar el uso en herramientas que esperan `/{id}`. Considera actualizar a `/{id}` para mejorar consistencia.

## 🗂 Docker & Docker Compose

El archivo `docker-compose.yml` levanta:
- `mariadb:10.11` en puerto `3306` (usuario `root`, contraseña `password123`)
- `phpmyadmin` en puerto `8090` (con acceso a la base `mariadb`)

Útil para desarrollo local y para no instalar MySQL directamente en el equipo.

## 🧭 Buenas prácticas para desarrollo

- Actualizar `application.properties` con credenciales seguras para producción.
- Añadir validaciones y manejo centralizado de errores (global exception handlers) para una API más robusta.
- Añadir tests unitarios y de integración para endpoints y servicios críticos.

## 🤝 Contribuciones

Si deseas contribuir:
1. Crea una rama para tus cambios
2. Envía un Pull Request con una descripción de los cambios
3. Asegúrate que todo compila y pasa tests

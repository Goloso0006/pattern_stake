# Sistema de Disco con Patron State

## 1. Descripcion 
Este proyecto implementa un sistema de disco usando Spring Boot y el patron de diseno **State**. El objetivo es que el comportamiento del disco cambie segun su estado actual, sin usar condicionales para controlar la logica principal.

## 2. funcionamiento (vision general)
El disco mantiene un estado interno. Cuando se ejecuta una accion (`read`, `write`, `reset`), esa accion se delega al estado actual. Cada estado decide que hacer, que mensaje devolver y a que estado cambiar.

## 3. Componentes principales
- **Contexto (`Disk`)**: guarda el estado actual y delega las operaciones.
- **Interfaz de estado (`DiskState`)**: define el contrato comun de comportamiento.
- **Estados concretos**:
  - `IdleState`
  - `ReadingState`
  - `WritingState`
  - `ErrorState`
- **Servicio (`DiskService`)**: coordina las operaciones del disco para el backend.
- **Controlador (`DiskController`)**: expone la API REST (`/disk/state`, `/disk/read`, `/disk/write`, `/disk/reset`).
- **Frontend (`index.html`)**: interfaz simple con botones para interactuar con la API y mostrar estado/mensaje.

## 4. Flujo 
1. El usuario interactua con el frontend.
2. El frontend llama a la API del backend.
3. El backend procesa la accion usando el patron State.
4. Se actualiza el estado y se retorna una respuesta JSON con `state` y `message`.

## 5. Ejecutable
- mvn spring-boot:run
- http://localhost:8080

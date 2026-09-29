# Sistema Clock Tower – PIN, seguridad, reportes y backup

## Funcionalidades implementadas

### Alta prioridad
| Funcionalidad | Descripción |
|---|---|
| **Cambio de PIN** | Botón en menú admin. Requiere PIN actual + nuevo (4–6 dígitos). |
| **Cerrar sesión** | Botón **CERRAR SESIÓN** confirma y vuelve al login. |

### Media prioridad
| Funcionalidad | Descripción |
|---|---|
| **Bitácora** | Tabla `bitacora_acceso` + botón **BITÁCORA DE ACCESOS**. Logins OK/fallidos, bloqueos, cambios de PIN. |
| **PIN numérico** | Solo dígitos, longitud 4 a 6. Validado en cliente y servidor. |
| **Mensaje de bloqueo** | **⚠ SESIÓN BLOQUEADA** con segundos restantes (más visible). |

### Baja prioridad
| Funcionalidad | Descripción |
|---|---|
| **Reportes** | Ventas del día (pedidos, ítems, monto) y productos más vendidos (Top 10). |
| **Backup BD** | Copia de `restaurante.db` a carpeta `backups/` con marca de tiempo. |

### Seguridad base (ya existente)
- PIN obligatorio solo para **admin**.
- Bloqueo temporal 60 s tras 5 intentos fallidos (contraseña o PIN).
- Contador de intentos por usuario en la BD.

## Archivos clave

**Servidor**
- `Autenticacion.java` – login, PIN, cambio de PIN, bitácora
- `ReportesDAO.java` – ventas del día y top productos
- `BackupDAO.java` – copia de seguridad de la BD
- `Servidor.java` – comandos `CHANGE_PIN`, `BITACORA`, `REPORTES`, `BACKUP`
- `ConexionSQLite.java` – migración de columnas + tabla bitácora

**Cliente**
- `LoginFrame.java` – config/verificación PIN, mensajes de bloqueo
- `MenuPrincipal.java` – Cambiar PIN, Bitácora, Reportes, Backup, Cerrar sesión

## Comandos de red (cliente → servidor)

```
LOGIN|usuario|contrasena
SET_PIN|usuario|pin
VERIFY_PIN|usuario|pin
CHANGE_PIN|usuario|pinActual|pinNuevo
BITACORA|50
REPORTES|ventas_dia
REPORTES|top_productos|10
BACKUP|crear
```

## Cómo probar

1. Iniciar **Servidor** (`servidor.Servidor`).
2. Iniciar **LoginFrame**.
3. Login `admin` / `1234` → configurar PIN (ej. `1234`).
4. Menú admin:
   - **CAMBIAR PIN** → PIN actual + nuevo.
   - **BITÁCORA DE ACCESOS** → ver eventos.
   - **REPORTES** → ventas del día o productos más vendidos.
   - **BACKUP BD** → crea copia en carpeta `backups/`.
   - **CERRAR SESIÓN** → vuelve al login.
5. Fallar 5 veces contraseña o PIN → mensaje de bloqueo 60 s.

Usuario de prueba: `admin` / `1234`.

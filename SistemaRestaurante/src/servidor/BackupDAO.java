package servidor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Backup de la base de datos SQLite desde el sistema.
 */
public class BackupDAO {

    /**
     * Copia restaurante.db a una carpeta de backups con timestamp.
     * Respuesta: BACKUP_OK|rutaCompleta  o  BACKUP_ERROR|mensaje
     */
    public static String crearBackup() {
        try {
            // Resolver ruta de la BD actual (misma lógica que ConexionSQLite)
            String userDir = System.getProperty("user.dir");
            String[] rutas = {
                userDir + java.io.File.separator + "restaurante.db",
                userDir + java.io.File.separator + "Bases De Datos" + java.io.File.separator + "restaurante.db",
                userDir + java.io.File.separator + ".." + java.io.File.separator + "Bases De Datos" + java.io.File.separator + "restaurante.db",
                "restaurante.db",
                "Bases De Datos/restaurante.db"
            };

            Path origen = null;
            for (String r : rutas) {
                Path p = Paths.get(r);
                if (Files.exists(p) && Files.isRegularFile(p)) {
                    origen = p.toAbsolutePath().normalize();
                    break;
                }
            }

            if (origen == null) {
                return "BACKUP_ERROR|No se encontró el archivo restaurante.db";
            }

            // Carpeta backups junto a la BD
            Path carpetaBackup = origen.getParent().resolve("backups");
            if (!Files.exists(carpetaBackup)) {
                Files.createDirectories(carpetaBackup);
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String nombre = "restaurante_backup_" + timestamp + ".db";
            Path destino = carpetaBackup.resolve(nombre);

            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

            return "BACKUP_OK|" + destino.toAbsolutePath().toString();
        } catch (IOException e) {
            return "BACKUP_ERROR|" + e.getMessage();
        } catch (Exception e) {
            return "BACKUP_ERROR|" + e.getMessage();
        }
    }
}

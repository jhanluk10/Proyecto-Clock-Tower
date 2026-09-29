package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    public boolean conectar() {

        try {
            socket = new Socket(HOST, PUERTO);

            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            salida = new PrintWriter(
                    socket.getOutputStream(), true);

            return true;

        } catch (IOException e) {

            System.err.println("No se pudo conectar con el servidor.");
            System.err.println("Error: " + e.getMessage());

            return false;
        }
    }

    public String enviarMensaje(String mensaje) {

    try {

        salida.println(mensaje);

        String respuesta = entrada.readLine();

        System.out.println("Respuesta del servidor: " + respuesta);

        return respuesta;

    } catch (IOException e) {

        System.err.println("Error al recibir respuesta: " + e.getMessage());

        return "ERROR: No se pudo comunicar con el servidor.";
    }
}

    public void cerrarConexion() {

        try {

            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {

            System.err.println("Error al cerrar la conexión.");
        }
    }
}
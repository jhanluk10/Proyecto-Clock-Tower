package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("   SERVIDOR DEL RESTAURANTE");
        System.out.println("=================================");

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado correctamente.");
            System.out.println("Esperando conexiones...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado correctamente.");
                atenderCliente(socket);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor.");
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void atenderCliente(Socket socket) {
        try (
            Socket cliente = socket;
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(
                    cliente.getOutputStream(), true)
        ) {
            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensaje);

                // CERRAR CONEXIÓN
                if (mensaje.equalsIgnoreCase("salir")) {
                    salida.println("Conexión finalizada. ¡Hasta luego!");
                    break;
                }

                // LOGIN
                if (mensaje.startsWith("LOGIN|")) {
                    String[] datos = mensaje.split("\\|");
                    if (datos.length == 3) {
                        String usuario = datos[1];
                        String contrasena = datos[2];
                        System.out.println("Intento de inicio de sesión:");
                        System.out.println("Usuario: " + usuario);
                        System.out.println("Contraseña recibida.");
                        String respuesta = Autenticacion.validarUsuario(usuario, contrasena);
                        System.out.println("Respuesta: " + respuesta);
                        salida.println(respuesta);
                    } else {
                        salida.println("LOGIN_ERROR|Datos de inicio de sesión incompletos.");
                    }

                // CONFIGURAR PIN (solo admin)
                } else if (mensaje.startsWith("SET_PIN|")) {
                    String[] datos = mensaje.split("\\|");
                    if (datos.length == 3) {
                        String usuario = datos[1];
                        String pin = datos[2];
                        System.out.println("Configurando PIN para: " + usuario);
                        String respuesta = Autenticacion.configurarPin(usuario, pin);
                        System.out.println("Respuesta: " + respuesta);
                        salida.println(respuesta);
                    } else {
                        salida.println("PIN_ERROR|Datos de PIN incompletos.");
                    }

                // VERIFICAR PIN
                } else if (mensaje.startsWith("VERIFY_PIN|")) {
                    String[] datos = mensaje.split("\\|");
                    if (datos.length == 3) {
                        String usuario = datos[1];
                        String pin = datos[2];
                        System.out.println("Verificando PIN para: " + usuario);
                        String respuesta = Autenticacion.verificarPin(usuario, pin);
                        System.out.println("Respuesta: " + respuesta);
                        salida.println(respuesta);
                    } else {
                        salida.println("PIN_ERROR|Datos de PIN incompletos.");
                    }

                // CAMBIAR PIN (admin autenticado)
                } else if (mensaje.startsWith("CHANGE_PIN|")) {
                    String[] datos = mensaje.split("\\|");
                    if (datos.length == 4) {
                        String usuario = datos[1];
                        String pinActual = datos[2];
                        String pinNuevo = datos[3];
                        System.out.println("Cambio de PIN para: " + usuario);
                        String respuesta = Autenticacion.cambiarPin(usuario, pinActual, pinNuevo);
                        System.out.println("Respuesta: " + respuesta);
                        salida.println(respuesta);
                    } else {
                        salida.println("PIN_ERROR|Datos de cambio de PIN incompletos.");
                    }

                // BITÁCORA de accesos
                } else if (mensaje.startsWith("BITACORA|")) {
                    String[] datos = mensaje.split("\\|");
                    int limite = 50;
                    if (datos.length >= 2) {
                        try { limite = Integer.parseInt(datos[1]); } catch (NumberFormatException ignored) {}
                    }
                    String respuesta = Autenticacion.listarBitacora(limite);
                    salida.println(respuesta);

                // USUARIOS
                } else if (mensaje.startsWith("USUARIO|")) {
                    String[] datos = mensaje.split("\\|");
                    if (datos.length == 5 && datos[1].equalsIgnoreCase("crear")) {
                        String usuario = datos[2];
                        String contrasena = datos[3];
                        String rol = datos[4];
                        System.out.println("Creando usuario:");
                        System.out.println("Usuario: " + usuario);
                        System.out.println("Rol: " + rol);
                        String respuesta = GestionUsuarios.agregarUsuario(usuario, contrasena, rol);
                        System.out.println("Respuesta: " + respuesta);
                        salida.println(respuesta);
                    } else if (datos.length == 3 && datos[1].equalsIgnoreCase("desactivar")) {
                        String usuario = datos[2];
                        System.out.println("Desactivando usuario: " + usuario);
                        String respuesta = GestionUsuarios.desactivarUsuario(usuario);
                        System.out.println("Respuesta: " + respuesta);
                        salida.println(respuesta);
                    } else if (datos.length == 2 && datos[1].equalsIgnoreCase("listar")) {
                        System.out.println("Consultando usuarios...");
                        String respuesta = GestionUsuarios.listarUsuarios();
                        System.out.println("Respuesta: " + respuesta);
                        salida.println(respuesta);
                    } else {
                        salida.println("USUARIO_ERROR|Datos de usuario incorrectos.");
                    }

                // MESAS
                } else if (mensaje.equalsIgnoreCase("MESAS|listar")) {
                    String respuesta = MesaDAO.obtenerMesas();
                    salida.println("MESAS_OK|" + respuesta);

                // PRODUCTOS
                } else if (mensaje.equalsIgnoreCase("PRODUCTOS|listar")) {
                    String respuesta = ProductoDAO.obtenerProductos();
                    salida.println("PRODUCTOS_OK|" + respuesta);

                // PRODUCTOS - ADMINISTRACIÓN
                } else if (mensaje.equalsIgnoreCase("PRODUCTOS|admin")) {
                    String respuesta = ProductoDAO.obtenerTodosLosProductos();
                    salida.println("PRODUCTOS_ADMIN_OK|" + respuesta);

                } else if (mensaje.startsWith("PRODUCTO|")) {
                    String[] datos = mensaje.split("\\|");

                    // CREAR PRODUCTO
                    if (datos.length == 6 && datos[1].equalsIgnoreCase("crear")) {
                        try {
                            String nombre = datos[2];
                            String descripcion = datos[3];
                            double precio = Double.parseDouble(datos[4]);
                            String categoria = datos[5];
                            System.out.println("Creando producto: " + nombre);
                            String respuesta = ProductoDAO.crearProducto(nombre, descripcion, precio, categoria);
                            System.out.println("Respuesta: " + respuesta);
                            salida.println(respuesta);
                        } catch (NumberFormatException e) {
                            salida.println("PRODUCTO_ERROR|El precio no es válido.");
                        }

                    // EDITAR PRODUCTO
                    } else if (datos.length == 7 && datos[1].equalsIgnoreCase("editar")) {
                        try {
                            int idProducto = Integer.parseInt(datos[2]);
                            String nombre = datos[3];
                            String descripcion = datos[4];
                            double precio = Double.parseDouble(datos[5]);
                            String categoria = datos[6];
                            System.out.println("Editando producto: " + idProducto);
                            String respuesta = ProductoDAO.editarProducto(idProducto, nombre, descripcion, precio, categoria);
                            System.out.println("Respuesta: " + respuesta);
                            salida.println(respuesta);
                        } catch (NumberFormatException e) {
                            salida.println("PRODUCTO_ERROR|Los datos numéricos no son válidos.");
                        }

                    // CAMBIAR DISPONIBILIDAD
                    } else if (datos.length == 4 && datos[1].equalsIgnoreCase("disponibilidad")) {
                        try {
                            int idProducto = Integer.parseInt(datos[2]);
                            int disponible = Integer.parseInt(datos[3]);
                            if (disponible != 0 && disponible != 1) {
                                salida.println("PRODUCTO_ERROR|La disponibilidad debe ser 0 o 1.");
                            } else {
                                String respuesta = ProductoDAO.cambiarDisponibilidad(idProducto, disponible);
                                System.out.println("Respuesta: " + respuesta);
                                salida.println(respuesta);
                            }
                        } catch (NumberFormatException e) {
                            salida.println("PRODUCTO_ERROR|Los datos numéricos no son válidos.");
                        }

                    // ELIMINAR PRODUCTO
                    } else if (datos.length == 3 && datos[1].equalsIgnoreCase("eliminar")) {
                        try {
                            int idProducto = Integer.parseInt(datos[2]);
                            System.out.println("Eliminando producto: " + idProducto);
                            String respuesta = ProductoDAO.eliminarProducto(idProducto);
                            System.out.println("Respuesta: " + respuesta);
                            salida.println(respuesta);
                        } catch (NumberFormatException e) {
                            salida.println("PRODUCTO_ERROR|El ID del producto no es válido.");
                        }
                    } else {
                        salida.println("PRODUCTO_ERROR|Datos del producto incorrectos.");
                    }

                // PEDIDOS
                } else if (mensaje.startsWith("PEDIDO|")) {
                    String[] datos = mensaje.split("\\|");

                    // CREAR PEDIDO
                    if (datos.length == 3 && datos[1].equalsIgnoreCase("crear")) {
                        try {
                            int idMesa = Integer.parseInt(datos[2]);
                            System.out.println("Creando pedido para mesa: " + idMesa);
                            String respuesta = PedidoDAO.crearPedido(idMesa);
                            System.out.println("Respuesta: " + respuesta);
                            salida.println(respuesta);
                        } catch (NumberFormatException e) {
                            salida.println("PEDIDO_ERROR|El número de mesa no es válido.");
                        }

                    // CERRAR PEDIDO
                    } else if (datos.length == 3 && datos[1].equalsIgnoreCase("cerrar")) {
                        try {
                            int idPedido = Integer.parseInt(datos[2]);
                            System.out.println("Cerrando pedido: " + idPedido);
                            String respuesta = PedidoDAO.cerrarPedido(idPedido);
                            System.out.println("Respuesta: " + respuesta);
                            salida.println(respuesta);
                        } catch (NumberFormatException e) {
                            salida.println("PEDIDO_ERROR|El ID del pedido no es válido.");
                        }

                    // CANCELAR PEDIDO (libera la mesa)
                    } else if (datos.length == 3 && datos[1].equalsIgnoreCase("cancelar")) {
                        try {
                            int idPedido = Integer.parseInt(datos[2]);
                            System.out.println("Cancelando pedido: " + idPedido);
                            String respuesta = PedidoDAO.cancelarPedido(idPedido);
                            System.out.println("Respuesta: " + respuesta);
                            salida.println(respuesta);
                        } catch (NumberFormatException e) {
                            salida.println("PEDIDO_ERROR|El ID del pedido no es válido.");
                        }

                    } else {
                        salida.println("PEDIDO_ERROR|Datos del pedido incorrectos.");
                    }

                // DETALLE
                } else if (mensaje.startsWith("DETALLE|")) {
                    String[] datosDetalle = mensaje.split("\\|");
                    if (datosDetalle.length == 6 && datosDetalle[1].equalsIgnoreCase("crear")) {
                        try {
                            int idPedido = Integer.parseInt(datosDetalle[2]);
                            int idProducto = Integer.parseInt(datosDetalle[3]);
                            int cantidad = Integer.parseInt(datosDetalle[4]);
                            double precioUnitario = Double.parseDouble(datosDetalle[5]);
                            System.out.println("Agregando producto al pedido:");
                            System.out.println("Pedido: " + idPedido + " | Producto: " + idProducto + " | Cantidad: " + cantidad);
                            String respuesta = DetallePedidoDAO.agregarDetalle(idPedido, idProducto, cantidad, precioUnitario);
                            System.out.println("Respuesta: " + respuesta);
                            salida.println(respuesta);
                        } catch (NumberFormatException e) {
                            salida.println("DETALLE_ERROR|Datos numéricos inválidos.");
                        }
                    } else {
                        salida.println("DETALLE_ERROR|Datos del detalle incorrectos.");
                    }

                // REPORTES
                } else if (mensaje.startsWith("REPORTES|")) {
                    String[] datos = mensaje.split("\\|");
                    if (datos.length >= 2 && datos[1].equalsIgnoreCase("ventas_dia")) {
                        String respuesta = ReportesDAO.ventasDelDia();
                        salida.println(respuesta);
                    } else if (datos.length >= 2 && datos[1].equalsIgnoreCase("top_productos")) {
                        int limite = 10;
                        if (datos.length >= 3) {
                            try { limite = Integer.parseInt(datos[2]); } catch (NumberFormatException ignored) {}
                        }
                        String respuesta = ReportesDAO.productosMasVendidos(limite);
                        salida.println(respuesta);
                    } else {
                        salida.println("REPORTES_ERROR|Comando de reporte no reconocido. Use REPORTES|ventas_dia o REPORTES|top_productos");
                    }

                // BACKUP BD
                } else if (mensaje.equalsIgnoreCase("BACKUP|crear") || mensaje.startsWith("BACKUP|")) {
                    String respuesta = BackupDAO.crearBackup();
                    System.out.println("Backup: " + respuesta);
                    salida.println(respuesta);

                // MENSAJE NO RECONOCIDO
                } else {
                    salida.println("Servidor recibió: " + mensaje);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al atender cliente: " + e.getMessage());
        }
    }
}
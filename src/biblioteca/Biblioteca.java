/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca;

import Controlador.LibroControlador;
import Controlador.PrestamoControlador;
import Controlador.UsuarioControlador;
import java.util.Date;
import java.util.Scanner;
import modelos.Usuario;
import modelos.libro;
import modelos.prestamo;

/**
 *
 * @author anyi4
 */
public class Biblioteca {

       public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioControlador usuarioControlador = new UsuarioControlador();
           PrestamoControlador prestamoController = new PrestamoControlador();
           LibroControlador libroController = new LibroControlador();
        
        System.out.print("Ingrese su ID de usuario: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Usuario usuario = usuarioControlador.obtenerUsuarioPorId(idUsuario);
        if (usuario != null) {
            System.out.println(usuario.getApellidos() + "Hola " + usuario.getNombre());
        } else {
            System.out.print("Usuario no encontrado. Ingrese su nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese sus apellidos: ");
            String apellidos = scanner.nextLine();
            usuarioControlador.saludarOInsertarUsuario(idUsuario, nombre, apellidos);
        }
        System.out.println("Seleccione una opción:");
        System.out.println("1. Préstamo");
        System.out.println("2. Pago Préstamo");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el ID del libro: ");
                int idLibro = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                System.out.print("Ingrese la fecha de devolución (YYYY-MM-DD): ");
                String fechaDevolucionStr = scanner.nextLine();
                Date fechaDevolucion = java.sql.Date.valueOf(fechaDevolucionStr);
                prestamoController.realizarPrestamo(idUsuario, idLibro, fechaDevolucion);
                break;
            case 2:
                System.out.print("Ingrese el ID del préstamo: ");
                int idPrestamo = scanner.nextInt();
                prestamoController.realizarPagoPrestamo(idPrestamo);
                break;
            default:
                System.out.println("Opción no válida.");
        }
        // Cerrar el escáner
        scanner.close();

}
    
    private static void mostrarOpcionesAdicionales(Scanner scanner, UsuarioControlador usuarioController, LibroControlador libroController) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Seleccione una opción adicional:");
            System.out.println("1. Listar todos los usuarios");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    usuarioController.listarTodosLosUsuarios();
                    break;
                case 2:
                    libroController.listarTodosLosLibros();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}

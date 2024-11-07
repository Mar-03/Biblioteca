/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import conexiones.BDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.libro;

/**
 *
 * @author anyi4
 */
public class LibroControlador {
     public libro obtenerLibroPorId(int idLibro) {
        libro libro = null;
        String query = "SELECT * FROM libro WHERE id_libro = ?";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                libro = new libro();
                libro.setId_libro(rs.getInt("id_libro"));
                libro.setNombre_libro(rs.getString("nombre_libro"));
                libro.setPrecio_alquiler(rs.getDouble("precio_alquiler"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }
    public boolean insertarLibro(libro libro) {
        String query = "INSERT INTO libro (nombre_libro, precio_alquiler) VALUES (?, ?)";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, libro.getNombre_libro());
            ps.setDouble(2, libro.getPrecio_alquiler());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void gestionarLibro(int idLibro, String nombreLibro, double precioAlquiler) {
        libro libro = obtenerLibroPorId(idLibro);
        if (libro != null) {
            System.out.println("Libro ya existe: " + libro.getNombre_libro() + ", Precio de alquiler: $" + libro.getPrecio_alquiler());
        } else {
          libro nuevoLibro = new libro(0, nombreLibro, precioAlquiler);
            if (insertarLibro(nuevoLibro)) {
                System.out.println("Libro insertado correctamente: " + nombreLibro + ", Precio de alquiler: $" + precioAlquiler);
            } else {
                System.out.println("Error al insertar el libro.");
            }
        }
    }
    public void listarTodosLosLibros() {
        String query = "SELECT * FROM libro";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            List<libro> libros = new ArrayList<>();
            while (rs.next()) {
                libro libro = new libro();
                libro.setId_libro(rs.getInt("id_libro"));
                libro.setNombre_libro(rs.getString("nombre_libro"));
                libro.setPrecio_alquiler(rs.getDouble("precio_alquiler"));
                libros.add(libro);
            }
            for (libro libro : libros) {
                System.out.println("ID: " + libro.getId_libro() + ", Nombre: " + libro.getNombre_libro() + ", Precio de alquiler: $" + libro.getPrecio_alquiler());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

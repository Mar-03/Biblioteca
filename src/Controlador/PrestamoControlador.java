/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import GeneradorPDF.PDFGenerador;
import conexiones.BDConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import modelos.libro;
import modelos.prestamo;

/**
 *
 * @author anyi4
 */
public class PrestamoControlador {
    
    public prestamo obtenerPrestamoPorId(int idPrestamo) {
        prestamo prestamo = null;
        String query = "SELECT * FROM prestamo WHERE id_prestamo = ?";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idPrestamo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                prestamo = new prestamo();
                prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
                prestamo.setIdUsuario(rs.getInt("id_usuario"));
                prestamo.setIdLibro(rs.getInt("id_libro"));
                prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fecha_devolucion"));
                prestamo.setMontoPagado(rs.getDouble("monto_pagado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    public boolean insertarPrestamo(prestamo prestamo) {
        String query = "INSERT INTO prestamo (id_usuario, id_libro, fecha_prestamo, fecha_devolucion, monto_pagado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = BDConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, prestamo.getIdUsuario());
            ps.setInt(2, prestamo.getIdLibro());
            ps.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            ps.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            ps.setDouble(5, prestamo.getMontoPagado());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void realizarPrestamo(int idUsuario, int idLibro, Date fechaDevolucion) {
        Date fechaPrestamo = new Date();
       // Comprobar que la fecha de devolución no exceda los 3 días
        long diferencia = fechaDevolucion.getTime() - fechaPrestamo.getTime();
        long dias = diferencia / (1000 * 60 * 60 * 24);
        if (dias > 3) {
            System.out.println("Tiempo Límite Excedido. Por favor, elige una fecha de devolución que no exceda los 3 días.");
            return;
        }

       
        LibroControlador libroController = new LibroControlador();
        libro libro = libroController.obtenerLibroPorId(idLibro);
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        double montoPagado = libro.getPrecio_alquiler();

        // Crear el préstamo
       prestamo prestamo = new prestamo(0, idUsuario, idLibro, fechaPrestamo, fechaDevolucion, montoPagado);
        if (insertarPrestamo(prestamo)) {
            System.out.println("Préstamo realizado correctamente. ID del préstamo: " + prestamo.getIdPrestamo());
        } else {
            System.out.println("Error al realizar el préstamo.");
        }
    }

    public void realizarPagoPrestamo(int idPrestamo) {
        prestamo prestamo = obtenerPrestamoPorId(idPrestamo);
        if (prestamo == null) {
            System.out.println("Préstamo no encontrado.");
            return;
        }

        
        double montoPagado = prestamo.getMontoPagado();
        System.out.println("El monto a pagar por el préstamo es: $" + montoPagado);

        
        PDFGenerador.generarFactura(prestamo);

        System.out.println("Pago realizado y factura generada.");
    }
}
